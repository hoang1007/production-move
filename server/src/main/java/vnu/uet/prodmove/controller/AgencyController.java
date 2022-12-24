package vnu.uet.prodmove.controller;

import java.util.Collection;
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
    public ResponseEntity<?> getAllWarehouses(@RequestParam(name="agencyId") String agencyId) throws NumberFormatException, NotFoundException {
        try {
            Set<Warehouse> warehouses = (Set<Warehouse>)agencyService.getAllWarehouses(Integer.parseInt(agencyId));
            return ResponseEntity.ok().body(warehouses);
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

    @PostMapping(ApiConfig.AGENCY_TRANSFER_TO_WARRANTY)
    public ResponseEntity<Map<String, String>> transferToWarranty(@RequestBody Map<String, Object> info) {
        try {
            Integer warrantyId = Integer.parseInt((String)info.get("warrantyId"));
            List<Integer> productIds = (List<Integer>) info.get("productIds");
            agencyService.transferProductToWarrantyCenter(productIds, warrantyId);
            return ResponseEntity.ok().body(Map.of("message", "Transfer successfully."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("message", e.getMessage()));
        }
    }
}
