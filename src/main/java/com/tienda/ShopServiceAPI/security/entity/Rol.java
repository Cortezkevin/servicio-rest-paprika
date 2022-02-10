package com.tienda.ShopServiceAPI.security.entity;

import com.tienda.ShopServiceAPI.security.enums.RolName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol() {
    }

    public Rol(RolName rol_name) {
        this.rolName = rol_name;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public RolName getRol_name() {
        return rolName;
    }

    public void setRol_name(RolName rol_name) {
        this.rolName = rol_name;
    }
}
