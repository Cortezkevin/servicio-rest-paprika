package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;

import java.util.ArrayList;
import java.util.Collection;

public class SupplierMapper {

    private Integer id_supplier;
    private String name;
    private String address;
    private Integer phone;
    private String state;
    private Collection<String> itemsProducts = new ArrayList<>();

    public SupplierMapper(){

    }
    public SupplierMapper(Supplier s){
        this(s.getId_supplier(), s.getName(), s.getAddress(),s.getPhone(), s.getState(), nameItemsProducts(s.getItemsProducts()));
    }

    public static Collection<String> nameItemsProducts(Collection<Product> products){
        Collection<String> listStringProducts = new ArrayList<>();
        for(Product p: products){
            listStringProducts.add(p.getName());
        }
        return listStringProducts;
    }

    public SupplierMapper(Integer id_supplier, String name, String address, Integer phone, String state, Collection<String> itemsProducts) {
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
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public Integer getPhone() {
		return phone;
	}
	public String getState() {
		return state;
	}
	public Collection<String> getItemsProducts() {
		return itemsProducts;
	}
	public void setId_supplier(Integer id_supplier) {
		this.id_supplier = id_supplier;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setItemsProducts(Collection<String> itemsProducts) {
		this.itemsProducts = itemsProducts;
	}
        

}
