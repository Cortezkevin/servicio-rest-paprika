package com.tienda.ShopServiceAPI.entity;

import org.aspectj.lang.annotation.After;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	private String id_product;

	@ManyToOne
	@JoinColumn(name = "id_category", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_category) references categories(id_category)"))
	private Category category;

	@ManyToOne
	@JoinColumn(name = "id_supplier", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_supplier) references suppliers(id_supplier)"))
	private Supplier supplier;

	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "mark")
	@NotNull
	private String mark;
	
	@Column(name = "description")
	@NotNull
	private String description;
	
	@Column(name = "url_image")
	@NotNull
	private String url_image;

	@Column(name = "expiration_date")
	@NotNull
	private String expiration_date;

	@Column(name = "price")
	@NotNull
	private Double price;

	@Column(name = "stock")
	@NotNull
	private Integer stock;

	@Column(name = "state")
	@NotNull
	private String state;

	//@OneToOne(mappedBy = "product")
	//private Collection<OrderDetails> itemsOrderDetails = new ArrayList<>();

	@OneToOne(mappedBy = "product")
	private OrderDetails orderDetails;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String id_product, Category category, Supplier supplier, String name, String mark, String description, String url_image, String expiration_date, Double price, Integer stock, String state) {
		this.id_product = id_product;
		this.category = category;
		this.supplier = supplier;
		this.name = name;
		this.mark = mark;
		this.description = description;
		this.url_image = url_image;
		this.expiration_date = expiration_date;
		this.price = price;
		this.stock = stock;
		this.state = state;
	}

	public String getId_product() {
		return id_product;
	}

	public void setId_product(String id_product) {
		this.id_product = id_product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
