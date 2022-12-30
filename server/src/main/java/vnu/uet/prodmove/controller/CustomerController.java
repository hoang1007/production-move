package vnu.uet.prodmove.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.services.implement.CustomerService;

@RestController
@RequestMapping(ApiConfig.CUSTOMER)
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(ApiConfig.CUSTOMER_BY_EMAIL)
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(customerService.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(ApiConfig.CUSTOMER_BY_PHONE)
    public ResponseEntity<?> getByPhone(@RequestParam String phone) {
        try {
            return ResponseEntity.ok(customerService.findByPhone(phone));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(ApiConfig.CUSTOMER_CREATE)
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        try {
            return ResponseEntity
                    .ok(customerService.create(body.get("fullName"), body.get("email"), body.get("phone")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(ApiConfig.CUSTOMER_ORDERS)
    public ResponseEntity<?> orderProducts(@RequestBody Map<String, Object> body) {
        try {
            customerService.orderProducts((Collection<Integer>) body.get("productIds"),
                    (Integer) body.get("customerId"));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
