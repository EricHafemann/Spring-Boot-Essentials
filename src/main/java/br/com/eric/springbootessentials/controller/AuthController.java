package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.dto.LoginRequestDto;
import br.com.eric.springbootessentials.dto.RegisterRequestDto;
import br.com.eric.springbootessentials.dto.TokenResponseDto;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register (@RequestBody @Valid RegisterRequestDto requestDto) throws Exception {
        authenticationService.register(requestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login (@RequestBody @Valid LoginRequestDto requestDto) throws Exception {
        return authenticationService.login(requestDto);
    }
}
