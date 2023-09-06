package com.catalisa.estoque.testControllers;

import com.catalisa.estoque.controller.EntradaController;
import com.catalisa.estoque.controller.ProdutosController;
import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.ProdutosDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.mapping.ProdutoMapper;
import com.catalisa.estoque.model.Produtos;
import com.catalisa.estoque.service.EntradaService;
import com.catalisa.estoque.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutosController.class)
class testeProdutosController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProdutoService produtoService;
    @Mock
    private ProdutoMapper produtoMapper;

    @Test
    void testaListaProdutos() throws Exception {
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        EntradaDTO entradaDTO2 = new EntradaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<EntradaDTO> listaEntradas = new ArrayList<>();
        listaEntradas.add(entradaDTO);
        listaEntradas.add(entradaDTO2);


        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        SaidaDTO saidaDTO1 = new SaidaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<SaidaDTO> saidaDTOS = new ArrayList<>();
        saidaDTOS.add(saidaDTO);
        saidaDTOS.add(saidaDTO1);

        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                listaEntradas,
                saidaDTOS);

        ProdutosDTO produtosDTO1 = new ProdutosDTO("espelho"
                ,"espelho anti-reflexo"
                ,100.0,
                50,
                listaEntradas,
                saidaDTOS);
        List<ProdutosDTO> produtosDTOList = new ArrayList<>();
        produtosDTOList.add(produtosDTO1);
        produtosDTOList.add(produtosDTO);


        when(produtoService.listaProdutos()).thenReturn(produtosDTOList);

        String expectedJson = new ObjectMapper().writeValueAsString(produtosDTOList);

        mockMvc.perform(get("/produtos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(print());

        verify(produtoService).listaProdutos();

    }

    @Test
    void testaBuscaPorId() throws Exception {
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        EntradaDTO entradaDTO2 = new EntradaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<EntradaDTO> listaEntradas = new ArrayList<>();
        listaEntradas.add(entradaDTO);
        listaEntradas.add(entradaDTO2);


        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        SaidaDTO saidaDTO1 = new SaidaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<SaidaDTO> listaSaidas = new ArrayList<>();
        listaSaidas.add(saidaDTO);
        listaSaidas.add(saidaDTO1);

        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                listaEntradas,
                listaSaidas);

        ProdutosDTO produtosDTO1 = new ProdutosDTO("espelho"
                ,"espelho anti-reflexo"
                ,100.0,
                50,
                listaEntradas,
                listaSaidas);
        List<ProdutosDTO> produtosDTOList = new ArrayList<>();
        produtosDTOList.add(produtosDTO1);
        produtosDTOList.add(produtosDTO);

        when(produtoService.getProdutoById(1L)).thenReturn(Optional.of(produtosDTO));


        mockMvc.perform(get("/produtos/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(produtosDTO.getNome()))
                .andExpect(jsonPath("$.descricao").value(produtosDTO.getDescricao()))
                .andExpect(jsonPath("$.preco").value(produtosDTO.getPreco()))
                .andExpect(jsonPath("$.quantidade").value(produtosDTO.getQuantidade()))
                .andExpect(jsonPath("$.entrada[0].produtoId").value(entradaDTO.getProdutoId()))
                .andExpect(jsonPath("$.entrada[0].quantidade").value(entradaDTO.getQuantidade()))
                .andExpect(jsonPath("$.entrada[0].data").value(entradaDTO.getData()))
                .andExpect(jsonPath("$.entrada[1].produtoId").value(entradaDTO2.getProdutoId()))
                .andExpect(jsonPath("$.entrada[1].quantidade").value(entradaDTO2.getQuantidade()))
                .andExpect(jsonPath("$.entrada[1].data").value(entradaDTO2.getData()))
                .andExpect(jsonPath("$.saida[0].produtoId").value(saidaDTO.getProdutoId()))
                .andExpect(jsonPath("$.saida[0].quantidade").value(saidaDTO.getQuantidade()))
                .andExpect(jsonPath("$.saida[0].data").value(saidaDTO.getData()))
                .andExpect(jsonPath("$.saida[1].produtoId").value(saidaDTO1.getProdutoId()))
                .andExpect(jsonPath("$.saida[1].quantidade").value(saidaDTO1.getQuantidade()))
                .andExpect(jsonPath("$.saida[1].data").value(saidaDTO1.getData()))
                .andDo(print());

        verify(produtoService).getProdutoById(1L);

    }

    @Test
    void testaDeletaProduto() throws Exception{


        mockMvc.perform(delete("/produtos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(produtoService).deletaProduto(1L);

    }

    @Test
    void testaCadastraProduto() throws Exception{
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        EntradaDTO entradaDTO2 = new EntradaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<EntradaDTO> listaEntradas = new ArrayList<>();
        listaEntradas.add(entradaDTO);
        listaEntradas.add(entradaDTO2);


        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        SaidaDTO saidaDTO1 = new SaidaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<SaidaDTO> listaSaidas = new ArrayList<>();
        listaSaidas.add(saidaDTO);
        listaSaidas.add(saidaDTO1);

        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                listaEntradas,
                listaSaidas);



        when(produtoService.cadastraProduto(any(ProdutosDTO.class))).thenReturn(produtosDTO);


        String expectedJson = new ObjectMapper().writeValueAsString(produtosDTO);

        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson))
                .andExpect(status().isCreated())
                .andExpect(content().json((expectedJson)))
                .andDo(print());

        verify(produtoService).cadastraProduto(produtosDTO);
    }
    @Test
    void testaCadastraProdutoSemListaDeEntradaESaida() throws Exception{


        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                new ArrayList<>(),
                new ArrayList<>());



        when(produtoService.cadastraProduto(any(ProdutosDTO.class))).thenReturn(produtosDTO);


        String expectedJson = new ObjectMapper().writeValueAsString(produtosDTO);

        mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON)
                        .content(expectedJson))
                .andExpect(status().isCreated())
                .andExpect(content().json((expectedJson)))
                .andDo(print());

        verify(produtoService).cadastraProduto(produtosDTO);
    }
    @Test
    void testaAtulizaProduto() throws Exception{
        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                new ArrayList<>(),
                new ArrayList<>());

        ProdutosDTO produtosSalvo = new ProdutosDTO("Martelo","martelo de desempenar vidro",50.0,50,new ArrayList<>(),new ArrayList<>());


        when(produtoService.getProdutoById(1L)).thenReturn(Optional.of(produtosDTO));
        when(produtoService.editaProduto(1L,produtosDTO)).thenReturn(produtosSalvo);


        String expectedJson = new ObjectMapper().writeValueAsString(produtosDTO);
        String expectedJsonSalvo = new ObjectMapper().writeValueAsString(produtosSalvo);

        mockMvc.perform(put("/produtos/1").contentType(MediaType.APPLICATION_JSON)
                .content(expectedJson))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonSalvo))
                .andDo(print());

        verify(produtoService).editaProduto(eq(1L),any(ProdutosDTO.class));
    }
    @Test
    @Transactional
    void testaRegistrarEntrada() throws Exception{
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                100, String.valueOf(LocalDateTime.now()));
        List<EntradaDTO> entradaDTOList= new ArrayList<>();
        entradaDTOList.add(entradaDTO);

        ProdutosDTO produtosDTO = new ProdutosDTO("martelo"
                ,"martelo de desentortar vidro"
                ,50.0,
                50,
                entradaDTOList,
                new ArrayList<>());


        ProdutosDTO produtosSalvo = new ProdutosDTO("Martelo",
                "martelo de desempenar vidro",
                50.0,150,
                entradaDTOList,
                new ArrayList<>());


        when(produtoService.getProdutoById(1L)).thenReturn(Optional.of(produtosDTO));
        when(produtoService.atualizarListaEntradas(1L, entradaDTO)).thenReturn(produtosSalvo);

        ObjectMapper objectMapper = new ObjectMapper();
        String entradaJson = objectMapper.writeValueAsString(entradaDTO);
        String expectedJsonSalvo = objectMapper.writeValueAsString(produtosSalvo);

        mockMvc.perform(post("/1/entrada").contentType(MediaType.APPLICATION_JSON)
                        .content(entradaJson))
                        .andExpect(status().isOk())
                        .andExpect(content().json(expectedJsonSalvo))
                        .andDo(print());

        verify(produtoService).atualizarListaEntradas(eq(1L), any(EntradaDTO.class));

    }
    @Test
    void testaRregistrarSaida() throws Exception{

    }
}
