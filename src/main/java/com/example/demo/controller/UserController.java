package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.*;
import com.example.demo.models.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
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
    private UserService userService;

    @GetMapping
    public List<UserModel> listarUsuarios(){
        return userService.listarUsuarios();
    }

    @PostMapping("/register")
    public List<UserModel> cadastrarUsuario(@RequestBody UserModel user) {
        return userService.cadastrarUsuario(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<UserModel>> atualizarUsuario(@PathVariable Integer id, @RequestBody UserModel userAtualizado){
        List<UserModel> usuario = userService.atualizarUsuario(id, userAtualizado);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserModel>> deletarUsuario(@PathVariable Integer id) {
        List<UserModel> userModels = userService.deletarUsuario(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
        public String test() {
        return "GET /users/test está funcionando";
    }
}
    


