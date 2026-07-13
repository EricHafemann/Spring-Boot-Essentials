package br.com.eric.springbootessentials.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class TreinoDto {

    @NotNull
    private Integer alunoId;

    @NotBlank
    private String nome;

    @NotEmpty
    private List<Integer> exerciciosIds;
}
