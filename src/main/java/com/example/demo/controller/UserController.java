package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



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
        if (userRepository.findByEmailUser(user.getEmailUser()).isPresent()) {
            return "Email ja cadastrado, tente novamente.";
    } userRepository.save(user);
        return "Cadastro feito com sucesso.";

    
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> atualizarUser(@PathVariable Integer id, @RequestBody UserModel userAtualizado){
        return userRepository.findById(id)
            .map(userExistente -> {
                userExistente.setNomeUser(userAtualizado.getNomeUser());
                userExistente.setEmailUser(userAtualizado.getEmailUser());
                userExistente.setSenhaUser(userAtualizado.getSenhaUser());
                userExistente.setEnderecoUser(userAtualizado.getEnderecoUser());
                
                UserModel userSalvo = userRepository.save(userExistente);

                return ResponseEntity.ok(userSalvo);
            }) .orElse(ResponseEntity.notFound().build());

        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
    


