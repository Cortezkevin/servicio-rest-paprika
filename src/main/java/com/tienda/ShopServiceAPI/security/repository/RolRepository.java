package com.tienda.ShopServiceAPI.security.repository;

import com.tienda.ShopServiceAPI.security.entity.Rol;
import com.tienda.ShopServiceAPI.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolName(RolName rolName);

}
