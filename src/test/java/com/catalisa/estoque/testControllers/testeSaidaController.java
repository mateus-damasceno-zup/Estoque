package com.catalisa.estoque.testControllers;

import com.catalisa.estoque.controller.EntradaController;
import com.catalisa.estoque.controller.SaidaController;
import com.catalisa.estoque.dto.EntradaDTO;
import com.catalisa.estoque.dto.SaidaDTO;
import com.catalisa.estoque.service.SaidaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaidaController.class)
class testeSaidaController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SaidaService saidaService;

    @Test
    void testaListaSaida() throws Exception {
        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));
        SaidaDTO saidaDTO1 = new SaidaDTO(2L,
                10, String.valueOf(LocalDateTime.now()));
        List<SaidaDTO> saidaDTOS = new ArrayList<>();
        saidaDTOS.add(saidaDTO);
        saidaDTOS.add(saidaDTO1);

        when(saidaService.listaSaidas()).thenReturn(saidaDTOS);

        String expectedJson = new ObjectMapper().writeValueAsString(saidaDTOS);

        mockMvc.perform(get("/saidas").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(print());

        verify(saidaService).listaSaidas();

    }

    @Test
    void testaBuscaPorId() throws Exception {
        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));


        when(saidaService.getSaidaById(1L)).thenReturn(Optional.of(saidaDTO));


        mockMvc.perform(get("/saidas/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtoId").value(1L))
                .andExpect(jsonPath("$.quantidade").value(saidaDTO.getQuantidade()))
                .andExpect(jsonPath("$.data").value(saidaDTO.getData()))
                .andDo(print());

        verify(saidaService).getSaidaById(1L);

    }

    @Test
    void testaDeletaSaida() throws Exception{
        SaidaDTO saidaDTO = new SaidaDTO(1L,
                1, String.valueOf(LocalDateTime.now()));

        mockMvc.perform(delete("/saidas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(saidaService).deletaSaida(1L);

    }

}
