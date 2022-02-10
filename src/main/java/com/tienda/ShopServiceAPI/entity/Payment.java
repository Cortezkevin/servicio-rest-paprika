package com.tienda.ShopServiceAPI.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Payment")
public class Payment {

	@Id
	private String id_payment;
	
	@Column(name = "tipo_payment")
	private String tipo_payment;
	
	@Column(name = "state")
	private String state;

	@OneToMany(mappedBy = "payment")
	private Collection<Orders> itemsOrders = new ArrayList<>();
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public Payment(String id_payment, String tipo_payment, String state) {
		super();
		this.id_payment = id_payment;
		this.tipo_payment = tipo_payment;
		this.state = state;
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

	public Collection<Orders> getItemsOrders() {
		return itemsOrders;
	}

	public void setItemsOrders(Collection<Orders> itemsOrders) {
		this.itemsOrders = itemsOrders;
	}
}
