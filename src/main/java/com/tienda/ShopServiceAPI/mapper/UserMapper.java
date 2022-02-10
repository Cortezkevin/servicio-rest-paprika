package com.tienda.ShopServiceAPI.mapper;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.security.entity.Rol;
import com.tienda.ShopServiceAPI.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserMapper {

    private Integer id_user;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<Rol> roles = new HashSet<>();
    private Collection<String> itemsOrders = new ArrayList<>();

    public UserMapper() {
    }

    public UserMapper(User u) {
        this(u.getId_user(),u.getName(),u.getUsername(),u.getEmail(),u.getPassword(),u.getRoles(), itemsOrderDetails(u.getItemsOrders()));
    }

    public static Collection<String> itemsOrderDetails(Collection<Orders> order){
        Collection<String> listStringOrders = new ArrayList<>();
        for(Orders o: order){
            listStringOrders.add(o.getId_order()+" - "+o.getOrder_date());
        }
        return listStringOrders;
    }

    public UserMapper(Integer id_user, String name, String username, String email, String password, Set<Rol> roles, Collection<String> itemsOrders) {
        this.id_user = id_user;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.itemsOrders = itemsOrders;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Collection<String> getItemsOrders() {
        return itemsOrders;
    }

    public void setItemsOrders(Collection<String> itemsOrders) {
        this.itemsOrders = itemsOrders;
    }
}
