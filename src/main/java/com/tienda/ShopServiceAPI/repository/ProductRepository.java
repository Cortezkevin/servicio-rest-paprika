package com.tienda.ShopServiceAPI.repository;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(Category category);
	List<Product> findBySupplier(Supplier supplier);
	Optional<Product> findByName(String name);
}
