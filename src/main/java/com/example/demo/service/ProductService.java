package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import com.example.demo.models.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${SUPABASE_URL}")
    private String supabaseUrl;

    @Value("${SUPABASE_SERVICE_KEY}")
    private String serviceKey;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> listarProdutos() {
        Sort sort = Sort.by("nomeKimono").ascending();
        return productRepository.findAll(sort);
    }

    public ProductModel salvarProduto(
            String nomeKimono,
            Double precoKimono,
            Integer quantidadeKimono,
            MultipartFile imagem
    ) {

        try {
            String fileName = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
            String uploadUrl = supabaseUrl + "/storage/v1/object/produtos/" + fileName;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(imagem.getContentType()));
            headers.set("apikey", serviceKey);
            headers.set("Authorization", "Bearer " + serviceKey);

            HttpEntity<byte[]> request = new HttpEntity<>(imagem.getBytes(), headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(uploadUrl, HttpMethod.POST, request, String.class);

            String publicUrl = supabaseUrl + "/storage/v1/object/public/produtos/" + fileName;

            ProductModel produto = new ProductModel();
            produto.setNomeKimono(nomeKimono);
            produto.setPrecoKimono(precoKimono);
            produto.setQuantidadeKimono(quantidadeKimono);
            produto.setImagem(publicUrl);

            return productRepository.save(produto);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar produto", e);
        }
    }

    public List<ProductModel> atualizarProdutos(Integer id, ProductModel produtoAtualizado) {

        ProductModel produtoExistente = productRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        produtoExistente.setNomeKimono(produtoAtualizado.getNomeKimono());
        produtoExistente.setPrecoKimono(produtoAtualizado.getPrecoKimono());
        produtoExistente.setQuantidadeKimono(produtoAtualizado.getQuantidadeKimono());

        productRepository.save(produtoExistente);

        return listarProdutos();
    }

    public List<ProductModel> deletarProduto(Integer id) {

        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }

        return listarProdutos();
    }
}