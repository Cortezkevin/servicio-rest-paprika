package com.tienda.ShopServiceAPI.repository;

import java.util.Date;

import javax.transaction.Transactional;

import com.tienda.ShopServiceAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Transactional
	@Modifying
	@Query(value = "{call sp_register_product(:id_category, :id_supplier, :name,"
			+ " :mark, :description, :url_image, :expiration_date, :price, :stock)}", nativeQuery = true)
	void insert(@Param("id_category") String id_category, @Param("id_supplier") String id_supplier,
			@Param("name") String name, @Param("mark") String mark, @Param("description") String description,
			@Param("url_image") String url_image, @Param("expiration_date") String expiration_date, 
			@Param("price") Double price, @Param("stock") Integer stock);
}
