package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;

import java.util.ArrayList;
import java.util.Collection;

public class SupplierMapper {

    private String id_supplier;
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

    public SupplierMapper(String id_supplier, String name, String address, Integer phone, String state, Collection<String> itemsProducts) {
        this.id_supplier = id_supplier;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.state = state;
        this.itemsProducts = itemsProducts;
    }

}
