package com.tienda.ShopServiceAPI.repository;

import javax.transaction.Transactional;

import com.tienda.ShopServiceAPI.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String>{

	@Transactional
	@Modifying
	@Query(value = "{call sp_Register_Supplier(:name, :address, :phone)}", nativeQuery = true)
	void insert(@Param("name") String name, @Param("address") String address, @Param("phone") Integer phone);
}
