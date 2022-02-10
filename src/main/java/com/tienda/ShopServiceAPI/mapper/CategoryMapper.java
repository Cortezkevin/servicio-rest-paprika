package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.Product;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

public class CategoryMapper {

    private String id_category;
    private String name;
    private String description;
    private String url_image;
    private String state;
    private Collection<String> itemsProducts = new ArrayList<>();

    public CategoryMapper() {
    }

    public CategoryMapper(Category c) {
        this(c.getId_category(), c.getName(), c.getDescription(), c.getUrl_image(), c.getState(), nameItemsProducts(c.getItemsProducts()));
    }

    public static Collection<String> nameItemsProducts(Collection<Product> itemsProducts){
        Collection<String> listNames = new ArrayList<>();
        for(Product p: itemsProducts){
            listNames.add(p.getName());
        }
        return listNames;
    }

    public CategoryMapper(String id_category, String name, String description, String url_image, String state, Collection<String> itemsProducts) {
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

    public Collection<String> getItemsProducts() {
        return itemsProducts;
    }

    public void setItemsProducts(Collection<String> itemsProducts) {
        this.itemsProducts = itemsProducts;
    }
}
