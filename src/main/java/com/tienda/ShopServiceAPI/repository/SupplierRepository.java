package com.tienda.ShopServiceAPI.repository;


import com.tienda.ShopServiceAPI.entity.Supplier;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	Optional<Supplier> findByName(String name);
	
}
