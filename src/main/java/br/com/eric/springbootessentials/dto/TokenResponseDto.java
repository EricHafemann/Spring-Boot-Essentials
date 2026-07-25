package br.com.eric.springbootessentials.dto;

import lombok.*;


public record TokenResponseDto (String token, Long expiration) {
}
