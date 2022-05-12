package com.tienda.ShopServiceAPI.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "suppliers")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_supplier;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private Integer phone;
	
	@Column(name = "state")
	private String state;

	@OneToMany(mappedBy = "supplier")
	private Collection<Product> itemsProducts = new ArrayList<>();

	public Supplier() {
	}

	public Supplier(Integer id_supplier, String name, String address, Integer phone, String state, Collection<Product> itemsProducts) {
		this.id_supplier = id_supplier;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.state = state;
		this.itemsProducts = itemsProducts;
	}

	public Integer getId_supplier() {
		return id_supplier;
	}

	public void setId_supplier(Integer id_supplier) {
		this.id_supplier = id_supplier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Collection<Product> getItemsProducts() {
		return itemsProducts;
	}

	public void setItemsProducts(Collection<Product> itemsProducts) {
		this.itemsProducts = itemsProducts;
	}
}
