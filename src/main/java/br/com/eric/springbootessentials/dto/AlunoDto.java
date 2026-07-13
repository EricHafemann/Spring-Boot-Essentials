package br.com.eric.springbootessentials.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class AlunoDto {

    @NotNull
    private String nome;

    @NotNull
    private String email;
}
