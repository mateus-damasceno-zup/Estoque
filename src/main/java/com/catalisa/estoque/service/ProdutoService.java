package com.catalisa.estoque.service;

import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.mapping.EntradaMapper;
import com.catalisa.estoque.mapping.ProdutoMapper;
import com.catalisa.estoque.mapping.SaidaMapper;
import com.catalisa.estoque.model.Entrada;
import com.catalisa.estoque.model.Produtos;
import com.catalisa.estoque.model.Saida;
import com.catalisa.estoque.repository.EntradaRepository;
import com.catalisa.estoque.repository.ProdutoRepository;
import com.catalisa.estoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;
    @Autowired
    private final EntradaRepository entradaRepository;
    @Autowired
    private final SaidaRepository saidaRepository;
    @Autowired
    public ProdutoMapper produtoMapper;
    @Autowired
    public EntradaMapper entradaMapper;
    @Autowired
    public SaidaMapper saidaMapper;

    public ProdutoService(ProdutoRepository produtoRepository,
                          EntradaRepository entradaRepository,
                          SaidaRepository saidaRepository,
                          ProdutoMapper produtoMapper,
                          EntradaMapper entradaMapper,
                          SaidaMapper saidaMapper) {
        this.produtoRepository = produtoRepository;
        this.entradaRepository = entradaRepository;
        this.saidaRepository = saidaRepository;
        this.produtoMapper = produtoMapper;
        this.entradaMapper = entradaMapper;
        this.saidaMapper = saidaMapper;
    }

    public List<ProdutosDTO> listaProdutos() {
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
        return produto.map(p -> produtoMapper.produtoParaprodutoDTO(p));
    }

    public ProdutosDTO cadastraProduto(ProdutosDTO produtosDTO) {
        Produtos produtos = produtoMapper.dTOParaProduto(produtosDTO);
        Produtos produtosSaved = produtoRepository.save(produtos);
        return produtoMapper.produtoParaprodutoDTO(produtosSaved);
    }

    public ProdutosDTO editaProduto(Long id, ProdutosDTO produtosDTO) {
        Optional<Produtos> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            Produtos produtos = produtoMapper.dTOParaProduto(produtosDTO);
            Produtos produtosSalvo = produtoRepository.save(produtos);
            return produtoMapper.produtoParaprodutoDTO(produtosSalvo);
        }
        return null;

    }

    public void deletaProduto(Long id) {
        produtoRepository.deleteById(id);
    }


    @Transactional
    public ProdutosDTO atualizarListaEntradas(Long idProduto, EntradaDTO entradaDTO) {
        Optional<Produtos> produtoOptional = produtoRepository.findById(idProduto);

        if (produtoOptional.isPresent()) {
            Produtos produto = produtoOptional.get();

            // Converter a entradaDTO para a entidade Entrada
            Entrada entrada = entradaMapper.dTOParaEntrada(entradaDTO);

            // Associar a entrada ao produto
            entrada.setProdutos(produto);

            // Configurar a data da entrada
            entrada.setData(String.valueOf(LocalDateTime.now()));

            // Adicionar a entrada à lista de entradas do produto
            produto.getEntradas().add(entrada);

            // Atualizar a quantidade em estoque
            int quantidadeAtual = produto.getQuantidade();
            produto.setQuantidade(quantidadeAtual + entradaDTO.getQuantidade());

            // Salvar o produto atualizado
            Produtos produtoSalvo = produtoRepository.save(produto);

            // Converter o produto atualizado para DTO
            ProdutosDTO produtosDTOSalvo = new ProdutosDTO();
            produtosDTOSalvo.setNome(produtoSalvo.getNome());
            produtosDTOSalvo.setDescricao(produtoSalvo.getDescricao());
            produtosDTOSalvo.setPreco(produtoSalvo.getPreco());
            produtosDTOSalvo.setQuantidade(produtoSalvo.getQuantidade());

            List<EntradaDTO> listaEntrada = new ArrayList<>();
            for (Entrada entradaf : produto.getEntradas()) {
                listaEntrada.add(entradaMapper.entradaParaentradaDTO(entradaf));
            }
            produtosDTOSalvo.setEntrada(listaEntrada);

            List<SaidaDTO> listaSaida = new ArrayList<>();
            for (Saida saida : produto.getSaidas()) {
                listaSaida.add(saidaMapper.saidaParaSaidaDTO(saida));
            }
            produtosDTOSalvo.setSaida(listaSaida);

            return produtosDTOSalvo;
        }

        return null;
    }


    @Transactional
    public ProdutosDTO atualizarListaSaida(Long idProduto, SaidaDTO saidaDTO) {
        Optional<Produtos> produtoOptional = produtoRepository.findById(idProduto);

        if (produtoOptional.isPresent()) {
            Produtos produto = produtoOptional.get();

            // Converter a saidaDTO para a entidade Entrada
            Saida saida = saidaMapper.dTOParaSaida(saidaDTO);

            // Associar a saida ao produto
            saida.setProdutos(produto);

            // Configurar a data da saida
            saida.setData(String.valueOf(LocalDateTime.now()));

            // Adicionar a saida à lista de entradas do produto
            produto.getSaidas().add(saida);

            // Atualizar a quantidade em estoque
            int quantidadeAtual = produto.getQuantidade();
            produto.setQuantidade(quantidadeAtual + saidaDTO.getQuantidade());

            // Salvar o produto atualizado
            Produtos produtoSalvo = produtoRepository.save(produto);

            // Converter o produto atualizado para DTO
            ProdutosDTO produtosDTOSalvo = new ProdutosDTO();
            produtosDTOSalvo.setNome(produtoSalvo.getNome());
            produtosDTOSalvo.setDescricao(produtoSalvo.getDescricao());
            produtosDTOSalvo.setPreco(produtoSalvo.getPreco());
            produtosDTOSalvo.setQuantidade(produtoSalvo.getQuantidade());

            List<EntradaDTO> listaEntrada = new ArrayList<>();
            for (Entrada entradaf : produto.getEntradas()) {
                listaEntrada.add(entradaMapper.entradaParaentradaDTO(entradaf));
            }
            produtosDTOSalvo.setEntrada(listaEntrada);

            List<SaidaDTO> listaSaida = new ArrayList<>();
            for (Saida saidas : produto.getSaidas()) {
                listaSaida.add(saidaMapper.saidaParaSaidaDTO(saidas));
            }
            produtosDTOSalvo.setSaida(listaSaida);

            return produtosDTOSalvo;
        }

        return null;
    }

}
