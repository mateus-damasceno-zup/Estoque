package com.catalisa.estoque.controller;

import com.catalisa.estoque.dto.EntradaDTO;

import com.catalisa.estoque.service.EntradaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/entradas")
@Api(value = "controle de entradas")
public class EntradaController {

    @Autowired
    EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaDTO>> listaEntrada(){
        return ResponseEntity.ok(entradaService.listaEntradas());
    }
    @ApiOperation(value = "retorna uma entrada por busca de id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<EntradaDTO>> getEntradaPorId(@PathVariable Long id){

        return ResponseEntity.ok(entradaService.getEntradaById(id));
    }


    @DeleteMapping(path="/{id}")
    public void deletaEntrada (@PathVariable Long id){
        entradaService.deletaEntrada(id);
    }



}
