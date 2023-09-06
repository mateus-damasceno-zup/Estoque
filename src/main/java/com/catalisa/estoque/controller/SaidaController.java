package com.catalisa.estoque.controller;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.service.SaidaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/saidas")
@Api(value = "controle de saidas")
public class SaidaController {

    @Autowired
    SaidaService saidaService;

    @GetMapping
    public ResponseEntity<List<SaidaDTO>> listaEntrada(){
        return ResponseEntity.ok(saidaService.listaSaidas());
    }
    @ApiOperation(value = "retorna uma entrada por busca de id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<SaidaDTO>> getSaidaPorId(@PathVariable Long id){

        return ResponseEntity.ok(saidaService.getSaidaById(id));
    }


    @DeleteMapping(path="/{id}")
    public void deletaEntrada (@PathVariable Long id){
        saidaService.deletaSaida(id);
    }



}
