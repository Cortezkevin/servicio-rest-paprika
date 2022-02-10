package com.tienda.ShopServiceAPI.repository;

import javax.transaction.Transactional;

import com.tienda.ShopServiceAPI.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_Register_Category(:name, :description, :url_image)}", nativeQuery = true)
	void insert(@Param("name") String name, @Param("description") String description,
			@Param("url_image") String url_image);
}
