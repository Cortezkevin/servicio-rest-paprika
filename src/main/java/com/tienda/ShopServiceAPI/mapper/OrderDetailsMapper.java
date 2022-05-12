package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;

import javax.persistence.*;

public class OrderDetailsMapper {

    private Integer id_order_detail;
    private String orders;
    private String product;
    private Double unit_price;
    private Integer amount;
    private Double subtotal;
    private Double discount;
    private Double total;

    public OrderDetailsMapper() {
    }

    public OrderDetailsMapper(OrderDetails o) {
        this(o.getId_order_detail(),o.getOrders().getId_order()+" - "+o.getOrders().getUser().getName(),o.getProduct().getName(),
                o.getUnit_price(),o.getAmount(),o.getSubtotal(),o.getDiscount(),o.getTotal());
    }

    public OrderDetailsMapper(Integer id_order_detail, String orders, String product, Double unit_price, Integer amount, Double subtotal, Double discount, Double total) {
        this.id_order_detail = id_order_detail;
        this.orders = orders;
        this.product = product;
        this.unit_price = unit_price;
        this.amount = amount;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
    }

    public Integer getId_order_detail() {
        return id_order_detail;
    }

    public void setId_order_detail(Integer id_order_detail) {
        this.id_order_detail = id_order_detail;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
