package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserModel> listarUsuarios(){ //@GetMapping
        Sort sort = Sort.by("nomeUser").descending()
        .and(Sort.by("emailUser")).ascending();

        return userRepository.findAll(sort);
    }

    public List<UserModel> cadastrarUsuario(UserModel userModel){ //@PostMapping
        if(!userRepository.findByEmailUser(userModel.getEmailUser()).isPresent()){
            String senhaCrip = passwordEncoder.encode(userModel.getSenhaUser());
            userModel.setSenhaUser(senhaCrip);
            
            userRepository.save(userModel);
        } return listarUsuarios();
       
    }

    public List<UserModel> atualizarUsuario(Integer id, UserModel usuarioAtualizado){
        UserModel usuarioExistente = userRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);
            usuarioExistente.setNomeUser(usuarioAtualizado.getNomeUser());
            usuarioExistente.setEmailUser(usuarioAtualizado.getEmailUser());
            usuarioExistente.setSenhaUser(usuarioAtualizado.getSenhaUser());

            userRepository.save(usuarioAtualizado);

            return listarUsuarios();
    }

    public List<UserModel> deletarUsuario(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        } return listarUsuarios();
    }

    public Optional<UserModel> buscarPorEmail(String email){
    return userRepository.findByEmailUser(email);
}

}
