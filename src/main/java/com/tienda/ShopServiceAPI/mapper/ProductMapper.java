package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class ProductMapper {

    private String id_product;
    private String category;
    private String supplier;
    private String name;
    private String mark;
    private String description;
    private String url_image;
    private String expiration_date;
    private Double price;
    private Integer stock;
    private String state;

    public ProductMapper() {
    }

    public ProductMapper(Product p){
        this(p.getId_product(), p.getCategory().getName(), p.getSupplier().getName(), p.getName(), p.getMark(),
                p.getDescription(), p.getUrl_image(), p.getExpiration_date(), p.getPrice(), p.getStock(), p.getState());
    }

    public ProductMapper(String id_product, String category, String supplier, String name, String mark, String description, String url_image, String expiration_date, Double price, Integer stock, String state) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
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
