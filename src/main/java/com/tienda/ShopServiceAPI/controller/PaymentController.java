package com.tienda.ShopServiceAPI.controller;

import java.util.List;

import com.tienda.ShopServiceAPI.entity.Payment;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.PaymentMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/payment")
@CrossOrigin(origins = { "*" }, methods = RequestMethod.GET)
public class PaymentController {

	@Autowired
	private PaymentService service;

	@GetMapping(path = "/list")
	public ResponseEntity<List<PaymentMapper>> listar() {
		List<PaymentMapper> lista = (List<PaymentMapper>) MapperUtil.convertToPaymentMapper(service.findAll());
		if (lista.isEmpty()) {  
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<Payment> buscar(@PathVariable("id") String id) {
		Payment obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El elemento: " + id + ", no existe"));
		PaymentMapper paymentMapper = new PaymentMapper(obj);
		return new ResponseEntity(paymentMapper, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/insert")
	public ResponseEntity<?> insert(@RequestBody Payment p) {
		ResponseMessage respuesta = service.insert(p);
		if (respuesta.isRespuesta()) {
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Payment p, @PathVariable("id") String id) {
		Payment obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));
		obj.setTipo_payment(p.getTipo_payment());
		service.update(obj);
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));

		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
