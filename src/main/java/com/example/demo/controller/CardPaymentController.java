package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.CardPaymentModel;
import com.example.demo.service.CardPaymentService;

@RestController
@RequestMapping("/card-payments")
@CrossOrigin(origins = "*") // permite acesso do front-end (React, HTML, etc.)
public class CardPaymentController {

    private final CardPaymentService cardPaymentService;

    @Autowired
    public CardPaymentController(CardPaymentService cardPaymentService) {
        this.cardPaymentService = cardPaymentService;
    }

    // 🔹 Listar todos os pagamentos
    @GetMapping
    public List<CardPaymentModel> listarCartoes() {
        return cardPaymentService.listarCartao();
    }

    // 🔹 Buscar pagamento por ID
    @GetMapping("/{id}")
    public CardPaymentModel buscarPorId(@PathVariable Integer id) {
        return cardPaymentService.buscarPorId(id);
    }

    // 🔹 Cadastrar novo pagamento
    @PostMapping
    public CardPaymentModel cadastrarCartao(@RequestBody CardPaymentModel cardPaymentModel) {
        return cardPaymentService.cadastrarCartao(cardPaymentModel);
    }

    // 🔹 Atualizar pagamento existente
    @PutMapping("/{id}")
    public CardPaymentModel atualizarCartao(@PathVariable Integer id, @RequestBody CardPaymentModel cardPaymentModel) {
        return cardPaymentService.atualizarCartao(id, cardPaymentModel);
    }

    // 🔹 Deletar pagamento
    @DeleteMapping("/{id}")
    public void deletarCartao(@PathVariable Integer id) {
        cardPaymentService.deletarCartao(id);
    }
}
