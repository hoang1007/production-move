package vnu.uet.prodmove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vnu.uet.prodmove.repos.ProductlineRepository;


@RestController
public class HomeController {
    @Autowired
    private ProductlineRepository productlineRepository;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        var productline = productlineRepository.findById(100).get();

        return productline.getPhone();
    }
}
