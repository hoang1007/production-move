package vnu.uet.prodmove.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.entity.Customer;
import vnu.uet.prodmove.entity.Order;
import vnu.uet.prodmove.entity.ProductDetail;
import vnu.uet.prodmove.entity.Warehouse;
import vnu.uet.prodmove.exception.NotFoundException;
import vnu.uet.prodmove.services.IAgencyService;
import vnu.uet.prodmove.utils.dataModel.WarehouseModel;

@RestController
@RequestMapping(ApiConfig.AGENCY)
public class AgencyController {

    @Autowired
    private IAgencyService agencyService;

    /**
     * import all pending products into warehouse of agency.
     * 
     * @param agencyId id of the current agency
     * @param productIds list ids of pending products
     * @return message
     * @throws NotFoundException
     */
    @PostMapping(ApiConfig.AGENCY_IMPORT_PRODUCTS)
    public ResponseEntity<Map<String, String>> importPendingProducts(@RequestParam(name = "agencyId") String agencyId, @RequestParam(name = "warehouseId") String warehouseId, @RequestBody Map<String, Collection<String>> productIds) throws NotFoundException {
        try {
            agencyService.importPendingProductsFromFactory(Integer.parseInt(agencyId), Integer.parseInt(warehouseId), productIds.get("productIds"));
            return ResponseEntity.ok().body(Map.of("message", "Import products in warehouse successfully"));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
    
    /**
     * Create new warehouse in specific agency.
     * @param body WarehouseModel
     * @return message
     * @throws NotFoundException
     */
    @PostMapping(ApiConfig.AGENCY_CREATE_WAREHOUSE)
    public ResponseEntity<Map<String, String>> createWarehouse(@RequestParam(name="agencyId") String agencyId, @RequestBody WarehouseModel warehouseModel) throws NotFoundException {
        try {
            Warehouse warehouse = agencyService.createWarehouse(Integer.parseInt(agencyId), warehouseModel);
            return ResponseEntity.ok().body(Map.of("message", "Create warehouse successfully"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", "Something went wrong."));
        }
    }

    /**
     * Get all warehouses of agency
     * @param agencyId
     * @return array of warehouse
     * @throws NumberFormatException
     * @throws NotFoundException
     */
    @GetMapping(ApiConfig.AGENCY_ALL_WAREHOUSE)
    public ResponseEntity<Object> getAllWarehouses(@RequestParam(name="agencyId") String agencyId) throws NumberFormatException, NotFoundException {
        try {
            Set<Warehouse> warehouses = (Set<Warehouse>) agencyService.getAllWarehouses(Integer.parseInt(agencyId));
            // get online lately product detail
            warehouses.stream().forEach(warehouse -> {
                if (warehouse.getProductdetails().size() > 0) {
                    warehouse.setProductdetails(new HashSet<ProductDetail>(Arrays.asList(warehouse.getProductdetails().iterator().next())));
                }
            });
            List<Object> response = new ArrayList<Object>();
            for (Warehouse warehouse : warehouses) {
                response.add(new Object() {
                    public Integer id = warehouse.getId();
                    public String address = warehouse.getAddress();
                    public Set<ProductDetail> productDetails = warehouse.getProductdetails();
                    
                });
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }

    /**
     * Sell products to a customer
     * @param info Includes customerId and array of ID products.
     * @return
     */
    @PostMapping(ApiConfig.AGENCY_SELL_PRODUCT)
    public ResponseEntity<Map<String, String>> sellProducts(@RequestBody Map<String, Object> info) {
        try {
            Integer customerId = (Integer)info.get("customerId");
            List<Integer> productIds = (List<Integer>) info.get("productIds");
            agencyService.sellProducts(customerId, productIds);
            return ResponseEntity.ok().body(Map.of("message", "Sell successfully."));
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping(ApiConfig.AGENCY_ALL_ORDERS)
    public ResponseEntity<Object> getAllOrders(@RequestParam(name = "agencyId") String agencyId) {
        try {
            Set<Customer> customers = (HashSet) agencyService.getAllOrders(Integer.parseInt(agencyId));
            List<Object> responseObject = new ArrayList<Object>();
            for (Customer customer : customers) {
                if (customer != null) {
                    responseObject.add(new Object() {
                        public Integer id = customer.getId();
                        public String fullname = customer.getFullname();
                        public String phoneNumber = customer.getPhoneNumber();
                        public String email = customer.getEmail();
                        public Set<Order> orders = customer.getOrders();

                    });
                }
            }

            return ResponseEntity.ok().body(responseObject);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
}
