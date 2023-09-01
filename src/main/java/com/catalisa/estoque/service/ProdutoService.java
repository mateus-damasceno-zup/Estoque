package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.EstoqueDTO;
import com.catalisa.estoque.mapping.EstoqueMapper;
import com.catalisa.estoque.model.Estoque;
import com.catalisa.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public Optional<EstoqueDTO> getEstoqueById(Long id) {
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        if (estoque.isEmpty()) {

            return Optional.empty();
        }
        return estoque.map(a->estoqueMapper.estoqueTOEstoqueDTO(a));
    }
    public EstoqueDTO cadastraProduto(EstoqueDTO estoqueDTO) {
        Estoque estoque = estoqueMapper.dTOtoEstoque(estoqueDTO);
        Estoque estoqueSaved = estoqueRepository.save(estoque);
        return estoqueMapper.estoqueTOEstoqueDTO(estoqueSaved);
    }

    public EstoqueDTO atualizaEstoque(EstoqueDTO estoqueDTO){
        EstoqueDTO estoqueAtual = getEstoqueById()
        if (alunoAtual!=null){
            alunoAtual.setId(alunoAtual.getId());
            alunoAtual.setNome(estoqueDTO.getNome());
            alunoAtual.setEmail(estoqueDTO.getEmail());
            alunoAtual.setIdade(estoqueDTO.getIdade());
            Aluno aluno = mappingService.mapDTOToAluno(alunoAtual);
            Aluno alunoSalvo = alunoRepository.save(aluno);
            return mappingService.mapAlunoToDTO(alunoSalvo);
        }
        return null;
    }
    public void deletaProdutoEstoque(Long id) {
        estoqueRepository.deleteById(id);
    }

}
