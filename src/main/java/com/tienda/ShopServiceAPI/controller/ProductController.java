package com.tienda.ShopServiceAPI.controller;

import java.util.List;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.ProductMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.service.CategoryService;
import com.tienda.ShopServiceAPI.service.ProductService;
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
@RequestMapping(path = "/api/v1/product")
@CrossOrigin(origins = "*" , methods = RequestMethod.GET)
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SupplierService supplierService;

	@GetMapping(path = "/list")
	public ResponseEntity<List<ProductMapper>> findAll() {
		List<ProductMapper> list = (List<ProductMapper>) MapperUtil.convertToProductMapper(service.findAll());
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<ProductMapper> findById(@PathVariable("id") String id) {
		Product obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El Producto con id: " + id + " no existe"));
		ProductMapper productMapper = new ProductMapper(obj);
		return new ResponseEntity<>(productMapper, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/insert")
	public ResponseEntity<?> insert(@RequestBody Product p) {
		ResponseMessage resultado = service.insert(p);
		if (resultado.isRespuesta()) {
			return new ResponseEntity<>(resultado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Product p, @PathVariable("id") String id) {
		Product obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El Producto con id: " + id + " no existe"));
		Category category = categoryService.findById(p.getCategory().getId_category()).orElse(null);
		Supplier supplier = supplierService.findById(p.getSupplier().getId_supplier()).orElse(null);
		obj.setCategory(category);
		obj.setSupplier(supplier);
		obj.setName(p.getName());
		obj.setMark(p.getMark());
		obj.setDescription(p.getDescription());
		obj.setUrl_image(p.getUrl_image());
		obj.setExpiration_date(p.getExpiration_date());
		obj.setPrice(p.getPrice());
		obj.setStock(p.getStock());
		return new ResponseEntity<>(service.update(obj),HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		service.findById(id).orElseThrow(()-> new ResourceNotFoundException("El Producto con id: " + id + " no existe"));
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
