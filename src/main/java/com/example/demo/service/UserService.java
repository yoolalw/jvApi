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

@Service //coloca a anotacao que é um @Service
public class UserService {
    private UserRepository userRepository; //declara o userRepository

    @Autowired //notacao que adiciona todas as formas de "dependencia", basicamente, tudo que recebe essa notacao tem as dependencias e as funcoes adicionadas dentro dessa classe
    private PasswordEncoder passwordEncoder; //adiciona e declara todas as dependencias vindo do passwordEncoder

    public UserService(UserRepository userRepository){ //adiciona as depenencias do userService
        this.userRepository = userRepository; //tudo que for e vier do userRepository ira passar pelo UserService
    }

    public List<UserModel> listarUsuarios(){ //@GetMapping, basicamente esse listar usuario só vai gerar/mostrar tudo que ja ta adicionado dentro do banco
        Sort sort = Sort.by("nomeUser").descending() //Sort = organiza | Organiza por "nomeUser" de forma descrescente
        .and(Sort.by("emailUser")).ascending(); //E, organiza por "emailUser" de forma ascendente

        return userRepository.findAll(sort); // o userRepository faz a busca (findAll()) no banco, usando o parametro (sort), e retorna pra gente toda aquela lista no banco de dados/ requisicao
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

            userRepository.save(usuarioExistente);
            
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
