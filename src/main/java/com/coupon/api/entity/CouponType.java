package com.coupon.api.entity;

import lombok.Data;

import java.util.List;

@Data
public class CouponType {

    private String name;
    private List<Double> price;

}
