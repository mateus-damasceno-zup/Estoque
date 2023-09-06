package com.catalisa.estoque.controller;

import com.catalisa.estoque.dto.EntradaDTO;

import com.catalisa.estoque.service.EntradaService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/entradas")
@Tag(name="Entrada de produtos")
public class EntradaController {

    @Autowired
    EntradaService entradaService;

    @GetMapping
    @Operation(summary = "Busca todas as entradas de produtos", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    public ResponseEntity<List<EntradaDTO>> listaEntrada(){
        return ResponseEntity.ok(entradaService.listaEntradas());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<EntradaDTO>> getEntradaPorId(@PathVariable Long id){

        return ResponseEntity.ok(entradaService.getEntradaById(id));
    }


    @DeleteMapping(path="/{id}")
    public void deletaEntrada (@PathVariable Long id){
        entradaService.deletaEntrada(id);
    }



}
