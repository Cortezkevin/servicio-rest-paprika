package com.tienda.ShopServiceAPI.repository;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{

	List<OrderDetails> findByOrders(Orders orders);
	List<OrderDetails> findByProduct(Product product);
	
}
