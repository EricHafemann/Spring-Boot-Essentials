package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.config.TokenProvider;
import br.com.eric.springbootessentials.database.model.AlunosEntity;
import br.com.eric.springbootessentials.database.model.RolesEntity;
import br.com.eric.springbootessentials.database.repository.IAlunosRepository;
import br.com.eric.springbootessentials.database.repository.IRolesRepository;
import br.com.eric.springbootessentials.dto.LoginRequestDto;
import br.com.eric.springbootessentials.dto.RegisterRequestDto;
import br.com.eric.springbootessentials.dto.TokenResponseDto;
import br.com.eric.springbootessentials.enums.RoleType;
import br.com.eric.springbootessentials.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IAlunosRepository alunosRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private Long expirationTime;

    public void register (RegisterRequestDto dto) throws BadRequestException {

        AlunosEntity aluno = alunosRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if(aluno != null)
        {
            throw new BadRequestException("Aluno já cadastrado com esse email");
        }

        RolesEntity role = rolesRepository.findByNome(RoleType.ROLE_ALUNO.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                                .nome(RoleType.ROLE_ALUNO.name())
                        .build()));

        AlunosEntity alunosEntity = AlunosEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build();

        alunosRepository.save(alunosEntity);
    }

    public TokenResponseDto login(LoginRequestDto requestDto) throws BadRequestException {
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getSenha()));
            // authentication provider -> userdetailsservice -> passwordEncoder.matches() -> autenticado

            String token = tokenProvider.gerarToken(authentication);

            return new TokenResponseDto(token, expirationTime);

        }catch (BadCredentialsException e)
        {
            throw new BadRequestException("Credènciais Inválidas");
        }catch (Exception e)
        {
            throw e;
        }
    }
}
