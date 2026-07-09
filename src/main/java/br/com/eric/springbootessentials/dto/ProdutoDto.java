package br.com.eric.springbootessentials.dto;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoDto {

    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
}
