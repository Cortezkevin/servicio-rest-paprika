package com.tienda.ShopServiceAPI.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.tienda.ShopServiceAPI.entity.Orders;
import com.tienda.ShopServiceAPI.entity.Payment;
import com.tienda.ShopServiceAPI.entity.response.ResponseMessage;
import com.tienda.ShopServiceAPI.repository.OrdersRepository;
import com.tienda.ShopServiceAPI.repository.PaymentRepository;
import com.tienda.ShopServiceAPI.security.entity.User;
import com.tienda.ShopServiceAPI.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Orders> findAll() {
        return repository.findAll();
    }
    
    public List<Orders> listByUser(String username){
    	User u = userRepository.findByUsername(username).orElse(null);
    	return repository.findByUser(u);
    }
    
    public List<Orders> listByPayment(Integer tipo_payment){
    	Payment p = paymentRepository.findById(tipo_payment).orElse(null);
    	return repository.findByPayment(p);
    }

    public Optional<Orders> findById(Integer id) {
        Optional<Orders> obj = repository.findById(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        } else {
            return obj;
        }
    }

    public ResponseMessage insert(Orders o) {
        ResponseMessage resultado = new ResponseMessage();
        String mensaje = "";
        boolean respuesta = false;
        try {
            User user = userRepository.findById(o.getUser().getId_user()).orElse(null);
            Payment payment = paymentRepository.findById(o.getPayment().getId_payment()).orElse(null);
            if(user== null){
            	mensaje = "El usuario ingresado no existe";
            	respuesta = false;
            }else if(payment == null){
            	mensaje = "El tipo de pago ingresado no existe";
            	respuesta = false;
            }else {            
	            repository.save(o);
	            mensaje = "Pedido registrado correctamente";
	            respuesta = true;
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        resultado.setMensaje(mensaje);
        resultado.setRespuesta(respuesta);
        resultado.setFecharespuesta(new Date());
        return resultado;
    }

    /*public ResponseMessage update(Orders o) {
        ResponseMessage resultado = new ResponseMessage();
        String mensaje = "";
        boolean respuesta = false;
        try {
            User user = userRepository.findById(o.getUser().getId_user()).orElse(null);
            Payment payment = paymentRepository.findById(o.getPayment().getId_payment()).orElse(null);
            repository.update(user.getId_user(), payment.getId_payment(), o.getOrder_date(), o.getTotal(), o.getId_order());
            mensaje = "Pedido actualizado correctamente";
            respuesta = true;
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        resultado.setMensaje(mensaje);
        resultado.setRespuesta(respuesta);
        resultado.setFecharespuesta(new Date());
        return resultado;
    }
*/
    public HashMap<String, String> delete(Integer id) {
        HashMap<String, String> mensaje = new HashMap<String, String>();
        try {
            repository.deleteById(id);
            mensaje.put("Mensaje", "Orden eliminada correctamente");
        } catch (Exception e) {
            mensaje.put("Error", e.getMessage());
        }
        return mensaje;
    }
}
