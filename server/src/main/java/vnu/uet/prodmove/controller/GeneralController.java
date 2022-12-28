package vnu.uet.prodmove.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.config.ApiConfig;
import vnu.uet.prodmove.entity.Productline;
import vnu.uet.prodmove.services.IProductlineService;

@RestController
@RequestMapping("/")
public class GeneralController {

    @Autowired
    private IProductlineService productlineService;

    @GetMapping(ApiConfig.GET_PRODUCTLINE_INFO)
    public ResponseEntity<?> getProductlineInfo(@RequestParam Integer id) {
        try {
            Productline productline = productlineService.findById(id);

            return ResponseEntity.ok(productline);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }
}
