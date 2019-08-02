package com.cai.entity;

import java.util.Date;

public class Orders {
    private Integer id;

    private Date creationtime;

    private Integer tenantid;

    private String orderno;

    private Integer ordertype;

    private Integer userid;

    private Integer consumer;

    private Integer amount;

    private Integer discountamount;

    private Integer balancededuction;

    private Integer payamount;

    private String couponcode;

    private Integer status;

    private Date statustime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public Integer getTenantid() {
        return tenantid;
    }

    public void setTenantid(Integer tenantid) {
        this.tenantid = tenantid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getConsumer() {
        return consumer;
    }

    public void setConsumer(Integer consumer) {
        this.consumer = consumer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(Integer discountamount) {
        this.discountamount = discountamount;
    }

    public Integer getBalancededuction() {
        return balancededuction;
    }

    public void setBalancededuction(Integer balancededuction) {
        this.balancededuction = balancededuction;
    }

    public Integer getPayamount() {
        return payamount;
    }

    public void setPayamount(Integer payamount) {
        this.payamount = payamount;
    }

    public String getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode == null ? null : couponcode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStatustime() {
        return statustime;
    }

    public void setStatustime(Date statustime) {
        this.statustime = statustime;
    }
}