package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.mapping.EntradaMapper;
import com.catalisa.estoque.model.Entrada;
import com.catalisa.estoque.model.Produtos;
import com.catalisa.estoque.repository.EntradaRepository;
import com.catalisa.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {

    @Autowired
    private final EntradaRepository entradaRepository;
    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    public EntradaMapper entradaMapper;


    public EntradaService(EntradaRepository entradaRepository, ProdutoRepository produtoRepository, EntradaMapper entradaMapper) {
        this.entradaRepository = entradaRepository;
        this.produtoRepository = produtoRepository;
        this.entradaMapper = entradaMapper;
    }

    public List<EntradaDTO> listaEntradas() {
        Iterable<Entrada> entradaList = entradaRepository.findAll();
        List<EntradaDTO> entradaDTOList = new ArrayList<>();
        for (Entrada entrada : entradaList) {
            EntradaDTO entradaDTO = entradaMapper.entradaParaentradaDTO(entrada);
            entradaDTOList.add(entradaDTO);

        }
        return entradaDTOList;
    }


    public Optional<EntradaDTO> getEntradaById(Long id) {
        Optional<Entrada> entrada = entradaRepository.findById(id);
        if (entrada.isEmpty()) {

            return Optional.empty();
        }
        return entrada.map(p -> entradaMapper.entradaParaentradaDTO(p));
    }

    public void deletaEntrada(Long id) {
        entradaRepository.deleteById(id);
    }


}
