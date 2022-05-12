package com.tienda.ShopServiceAPI.controller;

import java.util.List;

import com.tienda.ShopServiceAPI.entity.OrderDetails;
import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.OrderDetailsMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.service.OrderDetailsService;
import com.tienda.ShopServiceAPI.service.OrdersService;
import com.tienda.ShopServiceAPI.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/orderDetails")
@CrossOrigin(origins = { "*" }, methods = RequestMethod.POST)
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService service;

	/*@Autowired
	private OrdersService ordersService;*/

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<OrderDetailsMapper>> listar() {
		List<OrderDetailsMapper> lista = (List<OrderDetailsMapper>) MapperUtil.convertToOrderDetailsMapper(service.findAll());
		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/listByOrder/{order}")
	public ResponseEntity<List<OrderDetailsMapper>> listByOrder(@PathVariable("order") Integer order){
		List<OrderDetailsMapper> lista = (List<OrderDetailsMapper>) MapperUtil.convertToOrderDetailsMapper(service.listByOrder(order));
		if (lista.isEmpty()) {
			return new ResponseEntity("No existen Detalles en el Pedido con id: "+order,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/listByProduct/{product}")
	public ResponseEntity<List<OrderDetailsMapper>> listByProduct(@PathVariable("product") String product){
		List<OrderDetailsMapper> list = (List<OrderDetailsMapper>) MapperUtil.convertToOrderDetailsMapper(service.listByProduct(product));
		if (list.isEmpty()) {
			return new ResponseEntity("No existen Detalles de Pedidos con el Producto: "+product,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDetailsMapper> buscar(@PathVariable("id") Integer id) {
		OrderDetails obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El elemento: " + id + ", no existe"));
		OrderDetailsMapper orderDetailsMapper = new OrderDetailsMapper(obj);
		return new ResponseEntity<>(orderDetailsMapper, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody OrderDetails o) {
		Product dataP = productService.findById(o.getProduct().getId_product()).orElse(null);		
		Double unit_price = dataP.getPrice();
		o.setUnit_price(unit_price);
		ResponseMessage respuesta = service.insert(o);
		if (respuesta.isRespuesta()) {
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/insertList")
	public ResponseEntity<?> insertList(@RequestBody List<OrderDetails> list) {
		// List<OrderDetails> l = list;
		ResponseMessage respuesta = new ResponseMessage();
		if (list != null) {
			for (OrderDetails o : list) {
				Double unit_price = o.getProduct().getPrice();
				o.setUnit_price(unit_price);
				respuesta = service.insert(o);				
			}
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@PutMapping
	public ResponseEntity<?> update(@RequestBody OrderDetails o) {
		OrderDetails obj = service.findById(o.getId_order_detail())
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));
		Orders orders = ordersService.findById(o.getOrders().getId_order()).orElse(null);
		Product product = productService.findById(o.getProduct().getId_product()).orElse(null);
		obj.setOrders(orders);
		obj.setProduct(product);
		obj.setAmount(o.getAmount());
		obj.setDiscount(o.getDiscount());
		ResponseMessage respuesta = service.update(obj);
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}*/

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));

		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
