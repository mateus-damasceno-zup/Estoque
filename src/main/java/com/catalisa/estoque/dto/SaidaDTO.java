package com.catalisa.estoque.dto;

import com.catalisa.estoque.model.Produtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaidaDTO {

    private static final long serialVersionUID = 1L;
    private Long produtoId;
    private Integer quantidade;
    private String data;


}
