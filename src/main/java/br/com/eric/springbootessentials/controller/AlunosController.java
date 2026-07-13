package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.database.model.AvaliacoesFisicasEntity;
import br.com.eric.springbootessentials.dto.AlunoDto;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import br.com.eric.springbootessentials.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
public class AlunosController {

    private final AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@Valid @RequestBody AlunoDto alunoDto) throws BadRequestException {
        alunoService.create(alunoDto);
    }

    @GetMapping("/{alunoId}/avaliacao")
    @ResponseStatus(HttpStatus.OK)
    public AvaliacoesFisicasEntity getAvaliacaoFisica (@PathVariable(value = "alunoId") Integer id) throws NotFoundException {
        return alunoService.getAlunoAvaliacao(id);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer alunoId) throws NotFoundException {
        alunoService.delete(alunoId);
    }
}
