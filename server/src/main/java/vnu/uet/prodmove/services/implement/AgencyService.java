package vnu.uet.prodmove.services.implement;

import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
    public void importPendingProductsFromFactory(Integer agencyId, Integer warehouseId, Collection<String> productIds) throws NotFoundException {
        Agency agency = this.findById(warehouseId);
        Warehouse warehouse = agency.getWarehouses()
                .stream()
                .filter(item -> item.getId() == warehouseId)
                .findFirst()
                .orElseGet(() -> null);
        if (warehouse == null)
            throw new NotFoundException("Cannot find warehouse in agency.");

        List<ProductDetail> oldProductDetails = new ArrayList<>();
        List<ProductDetail> newProductDetails = new ArrayList<>();
        
        List<Product> products = (List<Product>) productService
                .findAllByIds(productIds.stream().map(id -> Integer.parseInt(id)).collect(Collectors.toList()));

        for (Product product : products) {
            ProductDetail lastProductDetail = ProductDetailQuerier.of(product).getLast();
            oldProductDetails.add(lastProductDetail);
            ProductDetail newProductDetail = ProductDetailBuilder.of(product).exportToAgency(agency);
            newProductDetail.setStartAt(lastProductDetail.getStartAt());
            newProductDetail.markCompleted();
            newProductDetail.setWarehouse(warehouse);
            newProductDetail.setWarrantyCenter(lastProductDetail.getWarrantyCenter());
            newProductDetail.setFactory(lastProductDetail.getFactory());
            newProductDetails.add(newProductDetail);
        }

        // productDetailService.saveAll(oldProductDetails);
        productDetailService.saveAll(newProductDetails);
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
        warehouses.stream().forEach(w -> {
            w.setProductdetails(new HashSet<>(Arrays.asList(w.getProductdetails().iterator().next())));
        });
        return warehouses;
    }

    @Override
    public void sellProducts(Integer customerId, Collection<Integer> productIds) throws NotFoundException {
        List<Product> products = (List<Product>) productService.findAllByIds(productIds);
        Customer customer = customerService.findById(customerId);
        customerService.buyProducts(products, customer);
    }

    @Override
    public void recallProducts(Integer productlineId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void receiveNeedRepairProducts(Iterable<Integer> productIds) {
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
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Collection<Customer> getAllOrders(Integer agencyId) throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Set<Warehouse> warehouses = (Set<Warehouse>) this.getAllWarehouses(agencyId);
        Set<ProductDetail> productDetails = warehouses.stream().map(w -> {
            return w.getProductdetails().iterator().next();
        }).collect(Collectors.toSet());

        Set<Customer> customers = productDetails.stream().map(pd -> {
            return pd.getCustomer();
        }).collect(Collectors.toSet());
        return customers;
    }

    @Override
    public void importPendingProducts(Integer agencyId, Integer warehouseId, Collection<String> productIds) throws NotFoundException {
        Agency agency = this.findById(warehouseId);
        Warehouse warehouse = agency.getWarehouses()
                .stream()
                .filter(item -> item.getId() == warehouseId)
                .findFirst()
                .orElseGet(() -> null);
        if (warehouse == null)
            throw new NotFoundException("Cannot find warehouse in agency.");
    }
    
}
