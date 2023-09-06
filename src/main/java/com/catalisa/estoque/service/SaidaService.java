package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.mapping.SaidaMapper;
import com.catalisa.estoque.model.Entrada;
import com.catalisa.estoque.model.Saida;
import com.catalisa.estoque.repository.ProdutoRepository;
import com.catalisa.estoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {

    @Autowired
    private final SaidaRepository saidaRepository;

    @Autowired
    public SaidaMapper saidaMapper;


    public SaidaService(SaidaRepository saidaRepository,SaidaMapper saidaMapper) {
        this.saidaRepository = saidaRepository;
        this.saidaMapper = saidaMapper;
    }

    public List<SaidaDTO> listaSaidas() {
        Iterable<Saida> saidaList = saidaRepository.findAll();
        List<SaidaDTO> saidaDTOList = new ArrayList<>();
        for (Saida saida : saidaList) {
            SaidaDTO saidaDTO = saidaMapper.saidaParaSaidaDTO(saida);
            saidaDTOList.add(saidaDTO);

        }
        return saidaDTOList;
    }


    public Optional<SaidaDTO> getSaidaById(Long id) {
        Optional<Saida> saida = saidaRepository.findById(id);
        if (saida.isEmpty()) {

            return Optional.empty();
        }
        return saida.map(p -> saidaMapper.saidaParaSaidaDTO(p));
    }

    public void deletaSaida(Long id) {
        saidaRepository.deleteById(id);
    }


}
