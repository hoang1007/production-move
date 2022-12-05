package vnu.uet.prodmove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.repos.ProductlineRepository;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductlineRepository productlineRepository;

    @GetMapping("/get")
    public ResponseEntity<String> a() {
        return ResponseEntity.ok().body("okok");
    }

    /**
     * @return ProductlineRepository return the productlineRepository
     */
    public ProductlineRepository getProductlineRepository() {
        return productlineRepository;
    }

    /**
     * @param productlineRepository the productlineRepository to set
     */
    public void setProductlineRepository(ProductlineRepository productlineRepository) {
        this.productlineRepository = productlineRepository;
    }

}
