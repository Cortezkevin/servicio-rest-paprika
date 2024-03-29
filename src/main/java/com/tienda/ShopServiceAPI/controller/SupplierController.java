package com.tienda.ShopServiceAPI.controller;

import java.util.List;

import com.tienda.ShopServiceAPI.entity.Supplier;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.SupplierMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.service.SupplierService;
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
@RequestMapping(path = "/api/v1/supplier")
@CrossOrigin(origins = { "*" }, methods = RequestMethod.GET)
public class SupplierController {

	@Autowired
	private SupplierService service;
	
	@GetMapping
	public ResponseEntity<List<SupplierMapper>> findAll() {
		List<SupplierMapper> list = (List<SupplierMapper>) MapperUtil.convertToSupplierMapper(service.findAll());
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierMapper> findById(@PathVariable("id") Integer id) {
		Supplier obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor con id: " + id + " no existe"));
		SupplierMapper supplierMapper = new SupplierMapper(obj);
		return new ResponseEntity<>(supplierMapper, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Supplier s) {
		ResponseMessage resultado = service.insert(s);
		if (resultado.isRespuesta()) {
			return new ResponseEntity<>(resultado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Supplier s) {
		Supplier obj = service.findById(s.getId_supplier())
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor con el id: " + s.getId_supplier() + " no existe"));
		obj.setName(s.getName());
		obj.setAddress(s.getAddress());
		obj.setPhone(s.getPhone());		
		SupplierMapper objResult = new SupplierMapper(service.update(obj)); 
		return new ResponseEntity<>(objResult, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor con el id: " + id + " no existe"));
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
