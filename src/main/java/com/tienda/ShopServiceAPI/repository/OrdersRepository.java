package com.tienda.ShopServiceAPI.repository;

import java.util.Date;

import com.tienda.ShopServiceAPI.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, String>{

	@Modifying
	@Transactional
	@Query(value = "{call sp_Register_Orders(:id_user, :id_payment, :order_date, :total)}", nativeQuery = true)
	void insert(@Param("id_user") Integer id_user, @Param("id_payment") String id_payment,
			@Param("order_date") String order_date, @Param("total") Double total);
	
	@Modifying
	@Transactional
	@Query(value = "{call sp_Update_Orders(:id_user, :id_payment, :order_date, :total, :id_order)}", nativeQuery = true)
	void update(@Param("id_user") Integer id_user, @Param("id_payment") String id_payment,
			@Param("order_date") String order_date, @Param("total") Double total,
			@Param("id_order") String id_order);
}
