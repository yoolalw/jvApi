package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; 
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){ 
        this.productRepository = productRepository; 
    }

    public List<ProductModel> listarProdutos(){ 
        Sort sort = Sort.by("nomeKimono").ascending();
        return productRepository.findAll(sort);
    }

    public ProductModel salvarProduto(String nomeKimono, Double precoKimono, Integer quantidadeKimono, MultipartFile imagem) {
        try {
            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir));

            String nomeArquivo = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            Path caminhoArquivo = Paths.get(uploadDir, nomeArquivo);

            Files.copy(imagem.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);
            
            ProductModel produto = new ProductModel();
            produto.setNomeKimono(nomeKimono);
            produto.setPrecoKimono(precoKimono);
            produto.setQuantidadeKimono(quantidadeKimono);
            produto.setImagem(nomeArquivo);

            return productRepository.save(produto);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public List<ProductModel> atualizarProdutos(Integer id, ProductModel produtoAtualizado){ 
        ProductModel produtoExistente = productRepository.findById(id)
            .orElseThrow(NoSuchElementException::new); 

        produtoExistente.setNomeKimono(produtoAtualizado.getNomeKimono());
        produtoExistente.setPrecoKimono(produtoAtualizado.getPrecoKimono());
        produtoExistente.setQuantidadeKimono(produtoAtualizado.getQuantidadeKimono());

        productRepository.save(produtoExistente);
        return listarProdutos();
    }

    public List<ProductModel> deletarProduto(Integer id){ 
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }  
        return listarProdutos();
    }
}