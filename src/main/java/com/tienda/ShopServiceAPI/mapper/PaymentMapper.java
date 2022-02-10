package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Payment;

import java.util.ArrayList;
import java.util.Collection;

public class PaymentMapper {

    private String id_payment;
    private String tipo_payment;
    private String state;
    private Collection<String> itemsOrders = new ArrayList<>();

    public PaymentMapper() {
    }

    public PaymentMapper(Payment p) {
        this(p.getId_payment(), p.getTipo_payment(), p.getState(),itemsOrders(p.getItemsOrders()));
    }

    public static Collection<String> itemsOrders(Collection<Orders> orders){
        Collection<String> listStringOrders = new ArrayList<>();
        for(Orders o: orders){
            listStringOrders.add(o.getId_order());
        }
        return listStringOrders;
    }

    public PaymentMapper(String id_payment, String tipo_payment, String state, Collection<String> itemsOrders) {
        this.id_payment = id_payment;
        this.tipo_payment = tipo_payment;
        this.state = state;
        this.itemsOrders = itemsOrders;
    }

    public String getId_payment() {
        return id_payment;
    }

    public void setId_payment(String id_payment) {
        this.id_payment = id_payment;
    }

    public String getTipo_payment() {
        return tipo_payment;
    }

    public void setTipo_payment(String tipo_payment) {
        this.tipo_payment = tipo_payment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<String> getItemsOrders() {
        return itemsOrders;
    }

    public void setItemsOrders(Collection<String> itemsOrders) {
        this.itemsOrders = itemsOrders;
    }
}
