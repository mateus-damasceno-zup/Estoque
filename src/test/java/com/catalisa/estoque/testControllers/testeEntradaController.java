package com.catalisa.estoque.testControllers;

import com.catalisa.estoque.controller.EntradaController;
import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.service.EntradaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntradaController.class)
class testeEntradaController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EntradaService entradaService;

    @Test
    void testaListaEntrada() throws Exception {
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        EntradaDTO entradaDTO2 = new EntradaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<EntradaDTO> listaEntradas = new ArrayList<>();
        listaEntradas.add(entradaDTO);
        listaEntradas.add(entradaDTO2);

        when(entradaService.listaEntradas()).thenReturn(listaEntradas);

        String expectedJson = new ObjectMapper().writeValueAsString(listaEntradas);

        mockMvc.perform(get("/entradas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(print());

        verify(entradaService).listaEntradas();

    }

    @Test
    void testaBuscaPorId() throws Exception {
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));


        when(entradaService.getEntradaById(1L)).thenReturn(Optional.of(entradaDTO));


        mockMvc.perform(get("/entradas/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtoId").value(1L))
                .andExpect(jsonPath("$.quantidade").value(entradaDTO.getQuantidade()))
                .andExpect(jsonPath("$.data").value(entradaDTO.getData()))
                .andDo(print());

        verify(entradaService).getEntradaById(1L);

    }

    @Test
    void testaDeletaEntrada() throws Exception{
        EntradaDTO entradaDTO = new EntradaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));

        mockMvc.perform(delete("/entradas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(entradaService).deletaEntrada(1L);

    }

}
