package com.coupon.api.enums;


public enum CouponStatusEnum {
    UN_DISTRIBUTION(0,"未分配"),
    DISTRIBUTION(1,"已分配"),
    WRITEOFF(2,"已核销"),
    Invalid(3,"已失效");

    private int code;
    private String name;
    CouponStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }


    public  static   CouponStatusEnum getCouponStatusEnum(int code) {
        for (CouponStatusEnum value : values()) {
            if (value.getCode()==code){
                return  value;
            }
        }
        return UN_DISTRIBUTION;
    }
}
