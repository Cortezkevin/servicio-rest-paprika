package com.tienda.ShopServiceAPI.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Order_details")
public class OrderDetails {

	@Id	
	private String id_order_detail;

	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_order) references orders(id_order)"))
	private Orders orders;

	@OneToOne
	@JoinColumn(name = "id_product", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_product) references products(id_product)"))
	private Product product;

	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_details_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> itemsProducts = new HashSet<>();*/
	
	@Column(name = "unit_price")
	private Double unit_price;
	
	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	@Column(name = "discount")
	private Double discount;
	
	@Column(name = "total")
	private Double total;
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(String id_order_detail, Orders orders, Product product, Double unit_price, Integer amount, Double subtotal, Double discount, Double total) {
		this.id_order_detail = id_order_detail;
		this.orders = orders;
		this.product = product;
		this.unit_price = unit_price;
		this.amount = amount;
		this.subtotal = subtotal;
		this.discount = discount;
		this.total = total;
	}

	public String getId_order_detail() {
		return id_order_detail;
	}

	public void setId_order_detail(String id_order_detail) {
		this.id_order_detail = id_order_detail;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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
