package com.catalisa.estoque.controller;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/produtos")
public class ProdutosController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutosDTO>> listaProdutos(){
        return ResponseEntity.ok(produtoService.listaProdutos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ProdutosDTO>> getProdutoPorId(@PathVariable Long id){

        return ResponseEntity.ok(produtoService.getProdutoById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutosDTO> cadastraProduto(@RequestBody ProdutosDTO produtosDTO){
        ProdutosDTO produtoNovo = produtoService.cadastraProduto(produtosDTO);
        return new ResponseEntity<>(produtoNovo, HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ProdutosDTO atulizaProduto(@PathVariable Long id, @RequestBody ProdutosDTO produtosDTO){
        return produtoService.editaProduto(id,produtosDTO);
    }

    @DeleteMapping(path="/{id}")
    public void deletaProduto (@PathVariable Long id){
        produtoService.deletaProduto(id);
    }


    @PostMapping("/entrada")
    public ResponseEntity<ProdutosDTO> registrarEntrada(@RequestBody EntradaDTO entradaDTO) {
        ProdutosDTO produtoAtualizado = produtoService.entradaEstoque(entradaDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }
    @PostMapping("/saida")
    public ResponseEntity<ProdutosDTO> registrarSaida(@RequestBody SaidaDTO saidaDTO) {
        ProdutosDTO produtoAtualizado = produtoService.saidaEstoque(saidaDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }


}
