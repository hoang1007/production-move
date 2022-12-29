package vnu.uet.prodmove.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.services.IWarrantyCenterService;

@RestController
@RequestMapping(ApiConfig.WARRANTY_CENTER)
public class WarrantyCenterController {
    @Autowired
    private IWarrantyCenterService warrantyCenterService;

    @GetMapping(ApiConfig.ALL_NEED_REPAIR_PRODUCTS)
    public ResponseEntity<?> getNeedRepairProducts() {
        try {
            return ResponseEntity.ok(warrantyCenterService.getNeedRepairProducts());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping(ApiConfig.ALL_REPAIRING_PRODUCTS)
    public ResponseEntity<?> getRepairingProducts(@RequestParam Integer warrantyCenterId) {
        try {
            return ResponseEntity.ok(warrantyCenterService.getRepairingProducts(warrantyCenterId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @PostMapping(ApiConfig.RECEIVE_FROM_AGENCY)
    public ResponseEntity<String> receiveProductsFromAgency(Map<String, Object> body) {
        try {
            Iterable<Integer> productIds = (Iterable<Integer>) body.get("productIds");
            warrantyCenterService.receiveProductsFromAgency(productIds);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @PostMapping(ApiConfig.RETURN_TO_AGENCY)
    public ResponseEntity<String> returnProductsToAgency(Iterable<Integer> productIds) {
        try {
            warrantyCenterService.returnProductsToAgency(productIds);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @PostMapping(ApiConfig.RETURN_TO_FACTORY)
    public ResponseEntity<String> returnProductsToFactory(Iterable<Integer> productIds) {
        try {
            warrantyCenterService.returnProductsToFactory(productIds);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
