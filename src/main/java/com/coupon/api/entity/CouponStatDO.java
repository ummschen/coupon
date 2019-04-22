package com.coupon.api.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "coupon_stat")
public class CouponStatDO {
    @Id
    private Integer id;

    @Column(name = "business_code")
    private String businessCode;

    @Column(name = "channel_code")
    private String channelCode;

    @Column(name = "coupon_type")
    private String couponType;

    @Column(name = "write_off")
    private Integer writeOff;

    @Column(name = "un_write_off")
    private Integer unWriteOff;

    @Column(name = "coupon_total")
    private Integer couponTotal;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return business_code
     */
    public String getBusinessCode() {
        return businessCode;
    }

    /**
     * @param businessCode
     */
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    /**
     * @return channel_code
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * @param channelCode
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * @return coupon_type
     */
    public String getCouponType() {
        return couponType;
    }

    /**
     * @param couponType
     */
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    /**
     * @return write_off
     */
    public Integer getWriteOff() {
        return writeOff;
    }

    /**
     * @param writeOff
     */
    public void setWriteOff(Integer writeOff) {
        this.writeOff = writeOff;
    }

    /**
     * @return un_write_off
     */
    public Integer getUnWriteOff() {
        return unWriteOff;
    }

    /**
     * @param unWriteOff
     */
    public void setUnWriteOff(Integer unWriteOff) {
        this.unWriteOff = unWriteOff;
    }

    /**
     * @return coupon_total
     */
    public Integer getCouponTotal() {
        return couponTotal;
    }

    /**
     * @param couponTotal
     */
    public void setCouponTotal(Integer couponTotal) {
        this.couponTotal = couponTotal;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}