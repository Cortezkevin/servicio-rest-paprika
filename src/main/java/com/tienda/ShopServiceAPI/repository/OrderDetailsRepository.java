package com.tienda.ShopServiceAPI.repository;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String>{

	@Modifying
	@Transactional
	@Query(value = "{call sp_Register_Order_Details(:product_id, :amount, :discount)}", nativeQuery = true)
	void insert(@Param("product_id") String product_id, @Param("amount") Integer amount,
			@Param("discount") Double discount);
	
	@Modifying
	@Transactional
	@Query(value = "{call sp_Update_Order_Details(:id_order, :product_id, :amount, :discount, :id_order_detail)}", nativeQuery = true)
	void update(@Param("id_order") String id_order, @Param("product_id") String product_id, @Param("amount") Integer amount,
			@Param("discount") Double discount, @Param("id_order_detail") String id_order_detail);
}
