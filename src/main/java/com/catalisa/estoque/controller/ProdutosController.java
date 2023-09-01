package com.catalisa.estoque.controller;

import com.catalisa.estoque.dto.EstoqueDTO;
import com.catalisa.estoque.model.Estoque;
import com.catalisa.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/estoque")
public class EstoqueController {

    @Autowired
    EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> listaEstoque(){
        return ResponseEntity.ok(estoqueService.listaProdutos());
    }




}
