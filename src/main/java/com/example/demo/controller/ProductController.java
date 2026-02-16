package com.example.demo.controller;

import com.example.demo.models.ProductModel;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductModel> listarProdutos() {
        return productService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<ProductModel> adicionarProduto(
            @RequestParam("nomeKimono") String nomeKimono,
            @RequestParam("precoKimono") Double precoKimono,
            @RequestParam("quantidadeKimono") Integer quantidadeKimono,
            @RequestParam("imagem") MultipartFile imagem) {

        ProductModel produto = productService.salvarProduto(nomeKimono, precoKimono, quantidadeKimono, imagem);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<ProductModel>> atualizarProduto(
            @PathVariable Integer id,
            @RequestBody ProductModel produtoAtualizado) {
        List<ProductModel> produtos = productService.atualizarProdutos(id, produtoAtualizado);
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<ProductModel>> deletarProduto(@PathVariable Integer id) {
        List<ProductModel> produtos = productService.deletarProduto(id);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/imagem/{nomeArquivo}")
    public ResponseEntity<Resource> getImagem(@PathVariable String nomeArquivo) {
        try {
            Path caminho = Paths.get("uploads/").resolve(nomeArquivo);
            Resource resource = new UrlResource(caminho.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(caminho))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
