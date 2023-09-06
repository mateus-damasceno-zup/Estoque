package com.catalisa.estoque.dto;

import com.catalisa.estoque.model.Saida;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProdutosDTO {

    private static final long serialVersionUID = 1L;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private List<EntradaDTO> entrada = new ArrayList<>();
    private List<SaidaDTO> saida = new ArrayList<>();
}
