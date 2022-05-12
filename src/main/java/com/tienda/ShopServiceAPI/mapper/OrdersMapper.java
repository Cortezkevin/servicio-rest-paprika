package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Payment;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

public class OrdersMapper {

    private Integer id_order;
    private String user;
    private String payment;
    private String order_date;
    private Double discount;
    private Double total;
    private String state;
    private Collection<String> itemsOrderDetails = new ArrayList<>();

    public OrdersMapper() {
    }

    public OrdersMapper(Orders o) {
        this(o.getId_order(), o.getUser().getUsername(), o.getPayment().getTipo_payment(), o.getOrder_date(), o.getTotal(),o.getDiscount(), o.getState(), itemsOrderDetails(o.getItemsOrderDetails()));
    }

    public static Collection<String> itemsOrderDetails(Collection<OrderDetails> orderDetails){
        Collection<String> listStringOrderDetails = new ArrayList<>();
        for(OrderDetails o: orderDetails){
            listStringOrderDetails.add(o.getProduct().getName()+" - "+o.getAmount().toString());
        }
        return listStringOrderDetails;
    }
    
    public OrdersMapper(Integer id_order, String user, String payment, String order_date, Double discount, Double total,
			String state, Collection<String> itemsOrderDetails) {
		this.id_order = id_order;
		this.user = user;
		this.payment = payment;
		this.order_date = order_date;
		this.discount = discount;
		this.total = total;
		this.state = state;
		this.itemsOrderDetails = itemsOrderDetails;
	}

	public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Collection<String> getItemsOrderDetails() {
        return itemsOrderDetails;
    }

    public void setItemsOrderDetails(Collection<String> itemsOrderDetails) {
        this.itemsOrderDetails = itemsOrderDetails;
    }

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
        
}
