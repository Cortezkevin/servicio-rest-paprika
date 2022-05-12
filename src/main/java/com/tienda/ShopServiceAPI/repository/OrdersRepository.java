package com.tienda.ShopServiceAPI.repository;


import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Payment;
import com.tienda.ShopServiceAPI.security.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	List<Orders> findByUser(User user);
	List<Orders> findByPayment(Payment payment);
	
}
