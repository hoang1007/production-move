package vnu.uet.prodmove.services.implement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vnu.uet.prodmove.entity.Agency;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Product;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.entity.WarrantyCenter;
import vnu.uet.prodmove.enums.ProductStage;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.repos.AgencyRepository;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.services.ICustomerService;
import vnu.uet.prodmove.services.IProductService;
import vnu.uet.prodmove.services.IProductdetailService;
import vnu.uet.prodmove.services.IWarrantyCenterService;
import vnu.uet.prodmove.utils.builder.ProductDetailBuilder;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;
import vnu.uet.prodmove.utils.querier.ObjectQuerier;
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

    @Autowired
    private IWarrantyCenterService warrantyCenterService;

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
            lastProductDetail.markCompleted();
            oldProductDetails.add(lastProductDetail);
            ProductDetail newProductDetail = lastProductDetail.toBuilder().warehouse(warehouse)
                    .stage(ProductStage.EXPORT_TO_AGENCY).build();
            newProductDetail.markUncompleted();
            newProductDetails.add(newProductDetail);
        }
        productDetailService.saveAll(oldProductDetails);
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
        Set<Warehouse> warehouses = agency.getWarehouses().stream()
                .map(warehouse -> {
                    try {
                        return ObjectQuerier.of(warehouse).include("id", "address").get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toSet());
        return warehouses;
    }

    @Override
    public void sellProducts(Integer customerId, Collection<Integer> productIds) throws NotFoundException {
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
            ProductDetail productDetail = ProductDetailBuilder.of(product).waitToRepair();
            productDetail.setWarehouse(warehouse);
            productDetails.add(productDetail);
        }
        productDetailService.saveAll(productDetails);
    }

    @Override
    public void transferProductToWarrantyCenter(Iterable<Integer> productIds, Integer warrantyCenterId) throws NotFoundException {
        List<Product> products = (List<Product>) productService.findAllByIds(productIds);
        // WarrantyCenter warrantyCenter = warrantyCenterService.findById(warrantyCenterId);
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

}
