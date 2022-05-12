package com.tienda.ShopServiceAPI.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.OrdersMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.security.service.UserService;
import com.tienda.ShopServiceAPI.service.OrdersService;
import com.tienda.ShopServiceAPI.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/orders")
@CrossOrigin(origins = { "*" }, methods = RequestMethod.POST)
public class OrdersController {

	@Autowired
	private OrdersService service;

	/*@Autowired
	private UserService userService;

	@Autowired
	private PaymentService paymentService;*/

	@GetMapping
	public ResponseEntity<List<OrdersMapper>> listar() {
		List<OrdersMapper> lista = (List<OrdersMapper>) MapperUtil.convertToOrdersMapper(service.findAll());
		if (lista.isEmpty()) {  
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listByUser/{username}")
	public ResponseEntity<List<OrdersMapper>> listByUser(@PathVariable("username") String username){
		List<OrdersMapper> list = (List<OrdersMapper>) MapperUtil.convertToOrdersMapper(service.listByUser(username));
		if (list.isEmpty()) {  
			return new ResponseEntity("No existen Pedidos del Usuario: "+username,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/listByPago/{tipoPago}")
	public ResponseEntity<List<OrdersMapper>> listByPayment(@PathVariable("tipoPago") Integer tipoPago){
		List<OrdersMapper> list = (List<OrdersMapper>) MapperUtil.convertToOrdersMapper(service.listByPayment(tipoPago));
		if (list.isEmpty()) {  
			return new ResponseEntity("No existen Pedidos del Pago con id: "+tipoPago,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdersMapper> buscar(@PathVariable("id") Integer id) {
		Orders obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El elemento: " + id + ", no existe"));
		OrdersMapper ordersMapper = new OrdersMapper(obj);
		return new ResponseEntity<>(ordersMapper, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Orders o) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateNow = dateFormat.format(new Date());
		o.setOrder_date(dateNow);
		ResponseMessage respuesta = service.insert(o);
		if (respuesta.isRespuesta()) {
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
/*
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Orders o, @PathVariable("id") String id) {
		Orders obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));
		User user = userService.findById(o.getUser().getId_user()).orElse(null);
		Payment payment = paymentService.findById(o.getPayment().getId_payment()).orElse(null);
		obj.setUser(user);
		obj.setPayment(payment);
		//obj.setOrder_date(o.getOrder_date());
		obj.setTotal(o.getTotal());
		ResponseMessage respuesta = service.update(obj);
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
*/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));

		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
