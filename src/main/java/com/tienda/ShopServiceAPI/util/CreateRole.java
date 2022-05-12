package com.tienda.ShopServiceAPI.util;

import com.tienda.ShopServiceAPI.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRole implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        //comentar cuando ya esta creado en la bd
        /*Rol rol_admin = new Rol(RolName.ROLE_ADMIN);
        Rol rol_user = new Rol(RolName.ROLE_USER);
        rolService.save(rol_admin);
        rolService.save(rol_user);*/
    }
}
