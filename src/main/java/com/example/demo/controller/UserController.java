package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> listarUsuarios(){
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public String cadastrarUsuario(@RequestBody UserModel user) {
        if (userRepository.findByEmailUser(user.getEmailUser()) ){
            return "Email ja cadastrado, tente novamente.";
    } userRepository.save(user);
        return "Cadastro feito com sucesso.";

    
    }
}
    


