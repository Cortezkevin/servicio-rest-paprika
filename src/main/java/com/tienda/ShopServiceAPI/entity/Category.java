package com.tienda.ShopServiceAPI.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	private String id_category;
	
	@Column(name = "name")
 	private String name;
	
	@Column(name = "description")
 	private String description;
	
	@Column(name = "url_image")
	private String url_image;
	
	@Column(name = "state")
 	private String state;

	@OneToMany(mappedBy = "category")
	private Collection<Product> itemsProducts = new ArrayList<>();

	public Category() {
	}

	public Category(String id_category, String name, String description, String url_image, String state, Collection<Product> itemsProducts) {
		this.id_category = id_category;
		this.name = name;
		this.description = description;
		this.url_image = url_image;
		this.state = state;
		this.itemsProducts = itemsProducts;
	}

	public String getId_category() {
		return id_category;
	}

	public void setId_category(String id_category) {
		this.id_category = id_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
