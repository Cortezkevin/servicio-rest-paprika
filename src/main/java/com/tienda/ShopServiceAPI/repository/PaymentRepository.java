package com.tienda.ShopServiceAPI.repository;

import com.tienda.ShopServiceAPI.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, String>{

	@Modifying
	@Transactional
	@Query(value = "{call sp_Register_Payment(:tipo_payment)}", nativeQuery = true)
	void insert(@Param("tipo_payment") String tipo_payment);
	
}
