package br.com.eric.springbootessentials.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
