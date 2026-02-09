package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserModel> listarUsuarios(){ //@GetMapping
        Sort sort = Sort.by("prioridade").descending()
        .and(Sort.by("nomeUser"))
        .and(Sort.by("emailUser")).ascending();

        return userRepository.findAll(sort);
    }

    public List<UserModel> cadastrarUsuario(UserModel userModel){ //@PostMapping
        if(!userRepository.findByEmailUser(userModel.getEmailUser()).isPresent()){
            userRepository.save(userModel);
        } return listarUsuarios();
       
    }

    

}
