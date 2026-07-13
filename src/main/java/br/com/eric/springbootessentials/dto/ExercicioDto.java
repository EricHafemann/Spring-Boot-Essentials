package br.com.eric.springbootessentials.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ExercicioDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String grupoMuscular;

}
