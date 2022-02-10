package com.tienda.ShopServiceAPI.security.entity;

import com.tienda.ShopServiceAPI.entity.Orders;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_rol", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Collection<Orders> itemsOrders = new ArrayList<>();

    public User() {
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public Collection<Orders> getItemsOrders() {
        return itemsOrders;
    }

    public void setItemsOrders(Collection<Orders> itemsOrders) {
        this.itemsOrders = itemsOrders;
    }
}
