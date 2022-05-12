package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.Category;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Optional<Category> findById(Integer id) {
        Optional<Category> obj = repository.findById(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        } else {
            return obj;
        }
    }

	
	public ResponseMessage insert(Category c){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			//c.setState("Activo");
			repository.save(c);
			mensaje = "Categoria registrada correctamente";
			respuesta = true;
		} catch (Exception e) {
			mensaje = e.getMessage();
			respuesta = false;
		}
		resultado.setMensaje(mensaje);
		resultado.setFecharespuesta(new Date());
		resultado.setRespuesta(respuesta);	
		return resultado; 
	}
	
	public Category update(Category c){
		return repository.save(c);
	}

	public HashMap<String, String> delete(Integer id){
		HashMap<String, String> mensaje = new HashMap<String, String>();
		try {
			repository.deleteById(id);
			mensaje.put("Mensaje", "Categoria: "+ id +" eliminada correctamente");
		} catch (Exception e) {
			mensaje.put("Error", e.getMessage());
		}
		return mensaje;
	}
}
