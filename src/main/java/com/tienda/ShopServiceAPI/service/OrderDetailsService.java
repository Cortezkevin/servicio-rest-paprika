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
	private OrdersRepository ordersRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<OrderDetails> findAll() {
		return repository.findAll();
	}

	public Optional<OrderDetails> findById(String id) {
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
			repository.insert(product.getId_product(), o.getAmount(), o.getDiscount());
			mensaje = "Detalle de pedido registrado";
			respuesta = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		resultado.setFecharespuesta(new Date());
		return resultado;
	}

	public ResponseMessage update(OrderDetails o) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			Orders orders = ordersRepository.findById(o.getOrders().getId_order()).orElse(null);
			Product product = productRepository.findById(o.getProduct().getId_product()).orElse(null);
			repository.update(orders.getId_order(), product.getId_product(), o.getAmount(), o.getDiscount(), o.getId_order_detail());
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

	public HashMap<String, String> delete(String id) {
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
