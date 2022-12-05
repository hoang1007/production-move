package vnu.uet.prodmove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.services.IWarrantyCenterService;

@RestController
@RequestMapping(ApiConfig.WARRANTY_CENTER)
public class WarrantyCenterController {
    @Autowired
    private IWarrantyCenterService warrantyCenterService;

    @PostMapping(ApiConfig.RECEIVE_FROM_AGENCY)
    public ResponseEntity<String> receiveProductsFromAgency(Iterable<Integer> productIds) {
        try {
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
    public ResponseEntity<String> returnProductsToFactory(Iterable<Integer> productIds, Integer factoryId) {
        try {
            warrantyCenterService.returnProductsToFactory(productIds, factoryId);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
