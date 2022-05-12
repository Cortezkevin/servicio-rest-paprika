package com.tienda.ShopServiceAPI.security.service;

import com.tienda.ShopServiceAPI.security.entity.User;
import com.tienda.ShopServiceAPI.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public List<User> findAll(){return userRepository.findAll();}
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    //public Optional<User> findBYUsername(String username){return userRepository.findByUsername(username);}

}
