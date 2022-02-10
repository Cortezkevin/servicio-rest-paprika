package com.tienda.ShopServiceAPI.service;

import java.text.SimpleDateFormat;
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

    public Optional<Product> findById(String id) {
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
            repository.insert(category.getId_category(), supplier.getId_supplier(), p.getName(), p.getMark(),
                    p.getDescription(), p.getUrl_image(), p.getExpiration_date(), p.getPrice(), p.getStock());
            mensaje = "Producto registrado correctamente";
            respuesta = true;
        } catch (Exception e) {
            mensaje = e.getMessage();
        }
        resultado.setMensaje(mensaje);
        resultado.setFecharespuesta(new Date());
        resultado.setRespuesta(respuesta);
        return resultado;
    }

    public Product update(Product p) {
        return repository.save(p);
    }

    public HashMap<String, String> delete(String id) {
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
