package vnu.uet.prodmove.services.implement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Order;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.services.ICustomerService;
import vnu.uet.prodmove.services.IProductService;
import vnu.uet.prodmove.services.IProductdetailService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;
import vnu.uet.prodmove.utils.querier.ProductDetailQuerier;

@Service
public class AgencyService implements IAgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductdetailService productDetailService;

    @Autowired
    private ICustomerService customerService;

    @Override
    public Agency findById(Integer id) throws NotFoundException {
        Optional<Agency> wrapperAgency = agencyRepository.findById(id);
        if (wrapperAgency.isPresent()) {
            return wrapperAgency.get();
        } else {
            throw new NotFoundException("Cannot find this agency.");
        }
    }

    @Override
    public void importPendingProductsFromFactory(Integer agencyId, Integer warehouseId, Collection<String> productIds)
            throws NotFoundException {
        Agency agency = this.findById(agencyId);
        Warehouse warehouse = agency.getWarehouses()
                .stream()
                .filter(item -> item.getId() == warehouseId)
                .findFirst()
                .orElseGet(() -> null);
        if (warehouse == null)
            throw new NotFoundException("Cannot find warehouse in agency.");

        // List<ProductDetail> oldProductDetails = new ArrayList<>();
        // List<ProductDetail> newProductDetails = new ArrayList<>();

        List<Product> products = (List<Product>) productService
                .findAllByIds(productIds.stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList()));

        // for (Product product : products) {
        // ProductDetail lastProductDetail = ProductDetailQuerier.of(product).getLast();
        // oldProductDetails.add(lastProductDetail);
        // ProductDetail newProductDetail =
        // ProductDetailBuilder.of(product).exportToAgency(agency);
        // newProductDetail.setStartAt(lastProductDetail.getStartAt());
        // newProductDetail.markCompleted();
        // newProductDetail.copyForeignKey(lastProductDetail);
        // newProductDetail.setWarehouse(warehouse);
        // newProductDetails.add(newProductDetail);
        // }

        // // productDetailService.saveAll(oldProductDetails);
        // productDetailService.saveAll(newProductDetails);
        List<ProductDetail> details = new ArrayList<>();

        for (var product : products) {
            ProductDetail lastProductDetail = ProductDetailQuerier.of(product).getLast();

            if (lastProductDetail.getStage().equals(ProductStage.EXPORT_TO_AGENCY)) {
                lastProductDetail.markCompleted();
                lastProductDetail.setWarehouse(warehouse);
                details.add(lastProductDetail);
            } else {
                throw new NotFoundException("Product is not in factory.");
            }
        }

        productDetailService.saveAll(details);
    }

    @Override
    public Warehouse createWarehouse(Integer agencyId, WarehouseModel warehouseModel) throws NotFoundException {
        Agency agency = this.findById(agencyId);
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress(warehouseModel.getAddress());
        warehouse.setAgency(agency);
        return warehouseService.create(warehouse);
    }

    @Override
    public Collection<Warehouse> getAllWarehouses(Integer agencyId)
            throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Agency agency = this.findById(agencyId);
        Set<Warehouse> warehouses = agency.getWarehouses();
        return warehouses;
    }

    @Override
    public void sellProducts(Integer customerId, Collection<Integer> productIds)
            throws NotFoundException, CloneNotSupportedException {
        List<Product> products = (List<Product>) productService.findAllByIds(productIds);
        Customer customer = customerService.findById(customerId);
        customerService.buyProducts(products, customer);
    }

    @Override
    public void receiveNeedRepairProducts(Iterable<Integer> productIds, Integer warehouseId) throws NotFoundException {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        List<Product> products = (List<Product>) productService.findAllByIds(productIds);
        List<ProductDetail> productDetails = new ArrayList<>();
        for (Product product : products) {
            ProductDetail productDetail = ProductDetailBuilder.of(product).needRepair();
            productDetail.setWarehouse(warehouse);
            productDetails.add(productDetail);
        }
        productDetailService.saveAll(productDetails);
    }

    @Override
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId)
            throws NotFoundException {
        List<Product> products = (List<Product>) productService.findAllByIds(productIds);
        // WarrantyCenter warrantyCenter =
        // warrantyCenterService.findById(warrantyCenterId);
        List<ProductDetail> productDetails = new ArrayList<>();
        for (Product product : products) {
            ProductDetail productDetail = ProductDetailBuilder.of(product).repairing();
            productDetails.add(productDetail);
        }
        productDetailService.saveAll(productDetails);
    }

    @Override
    public void recallProducts(Integer productlineId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void receiveProductsFromWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void returnToCustomer(Integer productId, Integer customerId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    public Collection<Order> getAllOrders(Integer agencyId)
            throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Set<Warehouse> warehouses = (Set<Warehouse>) this.getAllWarehouses(agencyId);
        List<Product> products = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            List<Product> productsInWarehouse = warehouse.getProductdetails()
                    .stream()
                    .filter(pd -> pd.getStage() == ProductStage.EXPORT_TO_AGENCY && pd.completed())
                    .map(pd -> pd.getProduct())
                    .collect(Collectors.toList());
            products.addAll(productsInWarehouse);
        }
        Set<Order> orders = new HashSet<Order>();
        for (Product p : products) {
            if (p.getOrder() != null) {
                orders.add(p.getOrder());
            }
        }
        return orders;
    }

    @Override
    public Collection<Product> getPendingProducts(Integer agencyId) throws NotFoundException {
        // Agency agency = this.findById(agencyId);
        List<ProductDetail> pendingProductDetails = (List<ProductDetail>) productDetailService
                .findPendingProductDetails(agencyId);
        List<Product> pendingProducts = pendingProductDetails.stream().map(pd -> pd.getProduct())
                .collect(Collectors.toList());
        return pendingProducts;
    }

    @Override
    public Collection<Product> getDistributedProducts(Integer agencyId) {
        return productDetailService.findAll().stream()
                .filter(pd -> pd.getStage() == ProductStage.EXPORT_TO_AGENCY && pd.completed())
                .filter(pd -> pd.getAgency().getId() == agencyId).map(pd -> pd.getProduct())
                .collect(Collectors.toList());
    }
}
