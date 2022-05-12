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

	@GetMapping
	public ResponseEntity<List<PaymentMapper>> listar() {
		List<PaymentMapper> lista = (List<PaymentMapper>) MapperUtil.convertToPaymentMapper(service.findAll());
		if (lista.isEmpty()) {  
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentMapper> buscar(@PathVariable("id") Integer id) {
		Payment obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El elemento: " + id + ", no existe"));
		PaymentMapper paymentMapper = new PaymentMapper(obj);
		return new ResponseEntity<>(paymentMapper, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Payment p) {
		ResponseMessage respuesta = service.insert(p);
		if (respuesta.isRespuesta()) {
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Payment p) {
		Payment obj = service.findById(p.getId_payment())
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + p.getId_payment() + " no existe"));
		obj.setTipo_payment(p.getTipo_payment());		
		PaymentMapper objResult = new PaymentMapper(service.update(obj));
		return new ResponseEntity<>(objResult, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El usuario con el id: " + id + " no existe"));

		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
