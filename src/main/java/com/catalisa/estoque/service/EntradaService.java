package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.mapping.ProdutoMapper;
import com.catalisa.estoque.model.Produtos;
import com.catalisa.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoMapper produtoMapper;


    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public List<ProdutosDTO> listaProdutos(){
        Iterable<Produtos> produtosLista = produtoRepository.findAll();
        List<ProdutosDTO> produtosDTOList = new ArrayList<>();
        for (Produtos produtos : produtosLista) {
            ProdutosDTO produtosDTOs = produtoMapper.produtoParaprodutoDTO(produtos);
            produtosDTOList.add(produtosDTOs);
            
        }
        return produtosDTOList;
    }


    public Optional<ProdutosDTO> getProdutoById(Long id) {
        Optional<Produtos> produto = produtoRepository.findById(id);
        if (produto.isEmpty()) {

            return Optional.empty();
        }
        return produto.map(p-> produtoMapper.produtoParaprodutoDTO(p));
    }
    public ProdutosDTO cadastraProduto(ProdutosDTO produtosDTO) {
        Produtos produtos = produtoMapper.dTOParaProduto(produtosDTO);
        Produtos produtosSaved = produtoRepository.save(produtos);
        return produtoMapper.produtoParaprodutoDTO(produtosSaved);
    }

    public ProdutosDTO editaProduto(Long id,ProdutosDTO produtosDTO){
        Optional<Produtos> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
           Produtos produtos= produtoMapper.dTOParaProduto(produtosDTO);
            Produtos produtosSalvo = produtoRepository.save(produtos);
            return produtoMapper.produtoParaprodutoDTO(produtosSalvo);
        }
        return null;

    }
    public void deletaProduto(Long id) {
        produtoRepository.deleteById(id);
    }


}
