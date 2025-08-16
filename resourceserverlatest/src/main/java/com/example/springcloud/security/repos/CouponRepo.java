package com.example.springcloud.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springcloud.security.entities.Coupon;


public interface CouponRepo extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
