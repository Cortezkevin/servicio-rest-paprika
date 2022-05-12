package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.OrderDetailsRepository;
import com.tienda.ShopServiceAPI.repository.OrdersRepository;
import com.tienda.ShopServiceAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

	@Autowired
	private OrderDetailsRepository repository;

	@Autowired
	private OrdersRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public List<OrderDetails> findAll() {
		return repository.findAll();
	}

	public List<OrderDetails> listByOrder(Integer orderId){
		Orders o = orderRepository.findById(orderId).orElse(null); 
		return repository.findByOrders(o);
	}
	
	public List<OrderDetails> listByProduct(String name){
		Product p = productRepository.findByName(name).orElse(null);
		return repository.findByProduct(p);
	}
	
	public Optional<OrderDetails> findById(Integer id) {
		Optional<OrderDetails> obj = repository.findById(id);
		if (obj.isEmpty()) {
			return Optional.empty();
		} else {
			return obj;
		}
	}

	public ResponseMessage insert(OrderDetails o) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			Product product = productRepository.findById(o.getProduct().getId_product()).orElse(null);
			if(product == null) {
				mensaje = "El producto ingresado no existe";
				respuesta = false;
			}else {			
				repository.save(o);
				mensaje = "Detalle de pedido registrado";
				respuesta = true;
			}
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		resultado.setFecharespuesta(new Date());
		return resultado;
	}
	
	/*
	public ResponseMessage update(OrderDetails o) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			Orders orders = ordersRepository.findById(o.getOrders().getId_order()).orElse(null);
			Product product = productRepository.findById(o.getProduct().getId_product()).orElse(null);
			repository.save(o);
			mensaje = "Detalle de pedido actualizado correctamente";
			respuesta = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		resultado.setFecharespuesta(new Date());
		return resultado;
	}
*/
	public HashMap<String, String> delete(Integer id) {
		HashMap<String, String> mensaje = new HashMap<String, String>();
		try {
			repository.deleteById(id);
			mensaje.put("Mensaje", "Detalle de pedido eliminado");
		} catch (Exception e) {
			 mensaje.put("Error", e.getMessage());
		}
		return mensaje;
	}
}
