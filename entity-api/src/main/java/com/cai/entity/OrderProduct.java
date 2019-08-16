package com.cai.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderProduct implements Serializable {
    private Integer id;

    private Date creationtime;

    private Integer tenantid;

    /**
     * 订单号
     */
    private String orderno;

    /**
     * 充值不产生订单行数据
     */
    private Integer lineno;

    /**
     * 产品编码
     */
    private String productcode;

    /**
     * 产品名称
     */
    private String productname;

    /**
     * 产品类型（包月，课程时间，试用）
     */
    private Integer producttype;

    /**
     * 产品容量
     */
    private Integer capacity;

    /**
     * 产品单位
     */
    private String unit;

    /**
     * 订购数量
     */
    private Integer count;

    /**
     * 单价
     */
    private Integer price;

    private Integer status;

    private Date statustime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationtime(Date date) {
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

    public Integer getLineno() {
        return lineno;
    }

    public void setLineno(Integer lineno) {
        this.lineno = lineno;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode == null ? null : productcode.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getProducttype() {
        return producttype;
    }

    public void setProducttype(Integer producttype) {
        this.producttype = producttype;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", creationtime=").append(creationtime);
        sb.append(", tenantid=").append(tenantid);
        sb.append(", orderno=").append(orderno);
        sb.append(", lineno=").append(lineno);
        sb.append(", productcode=").append(productcode);
        sb.append(", productname=").append(productname);
        sb.append(", producttype=").append(producttype);
        sb.append(", capacity=").append(capacity);
        sb.append(", unit=").append(unit);
        sb.append(", count=").append(count);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", statustime=").append(statustime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}