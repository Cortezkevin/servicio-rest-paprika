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

    public Optional<Orders> findById(String id) {
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
            repository.insert(user.getId_user(), payment.getId_payment(), o.getOrder_date(), o.getTotal());
            mensaje = "Pedido registrado correctamente";
            respuesta = true;
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        resultado.setMensaje(mensaje);
        resultado.setRespuesta(respuesta);
        resultado.setFecharespuesta(new Date());
        return resultado;
    }

    public ResponseMessage update(Orders o) {
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

    public HashMap<String, String> delete(String id) {
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
