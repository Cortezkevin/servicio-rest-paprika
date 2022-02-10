package com.tienda.ShopServiceAPI.controller;

import com.tienda.ShopServiceAPI.entity.Supplier;
import com.tienda.ShopServiceAPI.exception.ResourceNotFoundException;
import com.tienda.ShopServiceAPI.mapper.SupplierMapper;
import com.tienda.ShopServiceAPI.mapper.UserMapper;
import com.tienda.ShopServiceAPI.mapper.util.MapperUtil;
import com.tienda.ShopServiceAPI.security.entity.User;
import com.tienda.ShopServiceAPI.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<UserMapper>> findAll() {
        List<UserMapper> list = (List<UserMapper>) MapperUtil.convertToUserMapper(userService.findAll());
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<UserMapper> findById(@PathVariable("id") Integer id) {
        User obj = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El Usuario con id: " + id + " no existe"));
        UserMapper userMapper = new UserMapper(obj);
        return new ResponseEntity(userMapper, HttpStatus.OK);
    }
}
