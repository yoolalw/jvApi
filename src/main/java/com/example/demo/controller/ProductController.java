package com.example.demo.controller;

import com.example.demo.models.ProductModel;
import com.example.demo.repository.ProductRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

    
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductModel> listarProdutos(){
        return productRepository.findAll();
    }

    @PostMapping
    public ProductModel adicionarProduto(@RequestBody ProductModel produto){
        return productRepository.save(produto);
    }
}
