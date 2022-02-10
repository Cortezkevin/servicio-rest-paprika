package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.Payment;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;

	public List<Payment> findAll() {
		return repository.findAll();
	}

	public Optional<Payment> findById(String id) {
		Optional<Payment> obj = repository.findById(id);
		if (obj.isEmpty()) {
			return Optional.empty();
		} else {
			return obj;
		}
	}

	public ResponseMessage insert(Payment p) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;
		try {
			repository.insert(p.getTipo_payment());
			respuesta = true;
			mensaje = "Tipo de Pago registrado correctamente";			
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		resultado.setFecharespuesta(new Date());
		return resultado;
	}

	public Payment update(Payment p) {
		return repository.save(p);
	}

	public HashMap<String, String> delete(String id) {
		HashMap<String, String> mensaje = new HashMap<String, String>();
		try {
			repository.deleteById(id);
			mensaje.put("Mensaje", "Tipo de Pago eliminado correctamente");		
		} catch (Exception e) {
			mensaje.put("Error", e.getMessage());
		}
		return mensaje;
	}
}
