package vnu.uet.prodmove.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.services.IManufactoringBaseService;

@RestController
@RequestMapping(ApiConfig.FACTORY)
public class ManufacturingBaseController {

    @Autowired
    private IManufactoringBaseService factoryService;

    @PostMapping(ApiConfig.IMPORT_PRODUCTS)
    public ResponseEntity<String> importProducts(@RequestBody Map<String, Object> info) {
        try {
            Integer productlineId = (Integer) info.get("productlineId");
            Integer quantity = (Integer) info.get("quantity");
            Integer warehouseId = (Integer) info.get("warehouseId");

            factoryService.importProducts(productlineId, quantity, warehouseId);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

    @PostMapping(ApiConfig.EXPORT_TO_AGENCY)
    public ResponseEntity<String> exportToAgency(@RequestBody Map<String, Object> info) {
        try {
            Iterable<Integer> productIds = (Iterable<Integer>) info.get("productIds");
            Integer agencyId = (Integer) info.get("agencyId");

            factoryService.exportToAgency(productIds, agencyId);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

    @PostMapping(ApiConfig.RECEIVE_RETURNED_PRODUCTS)
    public ResponseEntity<String> receiveReturnedProducts(Iterable<Integer> productIDs) {
        try {
            factoryService.receiveReturnedProducts(productIDs);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
}
