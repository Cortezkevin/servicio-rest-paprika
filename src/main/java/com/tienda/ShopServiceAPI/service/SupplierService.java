package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.Supplier;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository repository;
	
	public List<Supplier> findAll(){
		return repository.findAll();
	}
	
	public Optional<Supplier> findById(Integer id){
		Optional<Supplier> obj = repository.findById(id);
		if(obj.isEmpty()) {
			return Optional.empty();
		}
		return obj;
	}
	
	public ResponseMessage insert(Supplier s){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			repository.save(s);
			mensaje = "Proveedor registrado correctamente";
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
	
	public Supplier update(Supplier s){		
		return repository.save(s);
	}

	public HashMap<String, String> delete(Integer id){
		HashMap<String, String> mensaje = new HashMap<String, String>();
		try {
			repository.deleteById(id);
			mensaje.put("Mensaje", "Proveedor: "+ id +" eliminado correctamente");
		} catch (Exception e) {
			mensaje.put("Error", e.getMessage());
		}
		return mensaje;
	}

}
