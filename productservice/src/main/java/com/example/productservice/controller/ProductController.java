package com.example.productservice.controller;

import com.example.productservice.dto.Coupon;
import com.example.productservice.model.Product;
import com.example.productservice.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepo repo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Product create(@RequestBody Product product){
        Coupon coupon=  restTemplate.getForObject("http://host.docker.internal:9901/api/coupon/"+product.getCouponCode(), Coupon.class);
        BigDecimal discount=coupon.getDiscount().divide(BigDecimal.valueOf(100));
        product.setPrice(product.getPrice().subtract(product.getPrice().multiply(discount)));
        return repo.save(product);
    }
}
