package com.example.demo.controller;


import com.example.demo.models.ProductModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    private ProductService productService;

    @GetMapping 
    public List<ProductModel> listarProdutos(){
        return productService.listarProdutos();
    }

    @PostMapping
    public List<ProductModel> adicionarProduto(@RequestBody ProductModel produto){
        return productService.criarProdutos(produto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<List<ProductModel>> atualizarProduto(@PathVariable Integer id, @RequestBody ProductModel produtoAtualizado){
        List<ProductModel> produtos = productService.atualizarProdutos(id, produtoAtualizado);
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}