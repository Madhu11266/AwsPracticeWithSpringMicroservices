package com.example.couponservice.controller;

import com.example.couponservice.model.Coupon;
import com.example.couponservice.repo.CouponRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private CouponRepo couponRepo;

    CouponController(CouponRepo couponRepo){
        this.couponRepo=couponRepo;
    }

    @PostMapping("/create")
    public Coupon create(@RequestBody Coupon coupon){
        return couponRepo.save(coupon);
    }

    @GetMapping("/{code}")
    public Coupon getCoupon(@PathVariable String code){
        return couponRepo.findByCode(code);
    }
}
