package vnu.uet.prodmove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.services.IWarehouseService;

@RestController
@RequestMapping(ApiConfig.WAREHOUSE)
public class WarehouseController {
    @Autowired
    private IWarehouseService warehouseService;
    
    @GetMapping(ApiConfig.NEW_PRODUCTS)
    public ResponseEntity<?> getNewProducts(@RequestParam(name = "id") Integer warehouseId) {
        try {
            return ResponseEntity.ok(warehouseService.getAllNewProducts(warehouseId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
}
