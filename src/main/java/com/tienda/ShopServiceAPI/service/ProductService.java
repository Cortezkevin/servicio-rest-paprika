package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.Product;
import com.tienda.ShopServiceAPI.entity.Supplier;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.CategoryRepository;
import com.tienda.ShopServiceAPI.repository.ProductRepository;
import com.tienda.ShopServiceAPI.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Product> findAll() {
        return repository.findAll();
    }
    
    public List<Product> listByCategory(String categoryName){
    	Category c = categoryRepository.findByName(categoryName).orElse(null);
    	return repository.findByCategory(c);
    }
    
    public List<Product> listBySupplier(String supplierName){
    	Supplier s = supplierRepository.findByName(supplierName).orElse(null);
    	return repository.findBySupplier(s);
    }

    public Optional<Product> findById(Integer id) {
        Optional<Product> obj = repository.findById(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        } else {
            return obj;
        }
    }

    public ResponseMessage insert(Product p) {
        ResponseMessage resultado = new ResponseMessage();
        String mensaje = "";
        boolean respuesta = false;
        try {
            Category category = categoryRepository.findById(p.getCategory().getId_category()).orElse(null);
            Supplier supplier = supplierRepository.findById(p.getSupplier().getId_supplier()).orElse(null);
            if(category == null) {
            	mensaje = "La Categoria ingresada no existe";
                respuesta = false;
            }else if(supplier == null) {
            	mensaje = "El Proveedor ingresado no existe";
                respuesta = false;
            }else {
	            repository.save(p);
	            mensaje = "Producto registrado correctamente";
	            respuesta = true;
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        resultado.setMensaje(mensaje);
        resultado.setFecharespuesta(new Date());
        resultado.setRespuesta(respuesta);
        return resultado;
    }

    public int update(Product p) {    	
    	Category category = categoryRepository.findById(p.getCategory().getId_category()).orElse(null);
        Supplier supplier = supplierRepository.findById(p.getSupplier().getId_supplier()).orElse(null);
        if(category == null) {
        	return 3;
        }else if(supplier == null) {
        	return 2;
        }else {       
        	repository.save(p);
        	return 1;
        }
    }

    public HashMap<String, String> delete(Integer id) {
        HashMap<String, String> mensaje = new HashMap<String, String>();
        try {
        	repository.deleteById(id);
        	mensaje.put("mensaje", "Producto eliminado correctamente");
        } catch (Exception e) {
            mensaje.put("error", e.getMessage());
        }
        return mensaje;
    }
}
