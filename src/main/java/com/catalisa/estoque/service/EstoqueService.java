package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.EstoqueDTO;
import com.catalisa.estoque.mapping.EstoqueMapper;
import com.catalisa.estoque.model.Estoque;
import com.catalisa.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EstoqueMapper estoqueMapper;

    public EstoqueService(EstoqueRepository estoqueRepository, EstoqueMapper estoqueMapper) {
        this.estoqueRepository = estoqueRepository;
        this.estoqueMapper = estoqueMapper;
    }

    public List<EstoqueDTO> listaProdutos(){
        Iterable<Estoque> estoques = estoqueRepository.findAll();
        List<EstoqueDTO> estoqueDTOList = new ArrayList<>();
        for (Estoque estoque: estoques) {
            EstoqueDTO estoqueDTOs = estoqueMapper.estoqueTOEstoqueDTO(estoque);
            estoqueDTOList.add(estoqueDTOs);
            
        }
        return estoqueDTOList;
    }
}
