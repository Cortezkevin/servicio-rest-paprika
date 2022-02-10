package com.tienda.ShopServiceAPI.controller;

import java.util.List;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.CategoryMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.service.CategoryService;
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
@RequestMapping(path = "/api/v1/category")
@CrossOrigin(origins = { "*" }, methods = RequestMethod.GET)
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping(path = "/list")
	public ResponseEntity<List<CategoryMapper>> findAll() {
		List<CategoryMapper> list = (List<CategoryMapper>) MapperUtil.convertToCategoryMapper(service.findAll());
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/findById/{id}")
	public ResponseEntity<CategoryMapper> findById(@PathVariable("id") String id) {
		Category obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con id: " + id + " no existe"));
		CategoryMapper categoryMapper = new CategoryMapper(obj);
		return new ResponseEntity<>(categoryMapper, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/insert")
	public ResponseEntity<?> insert(@RequestBody Category c) {
		ResponseMessage resultado = service.insert(c);
		if (resultado.isRespuesta()) {
			return new ResponseEntity<>(resultado, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(resultado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<?> update(@RequestBody Category c, @PathVariable("id") String id) {
		Category obj = service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con el id: " + id + " no existe"));
		obj.setName(c.getName());
		obj.setDescription(c.getDescription());
		obj.setUrl_image(c.getUrl_image());
		return new ResponseEntity<>(service.update(obj), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		service.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con el id: " + id + " no existe"));
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}

}
