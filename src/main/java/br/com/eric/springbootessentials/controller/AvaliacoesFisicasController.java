package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.dto.AvaliacaoFisicaDto;
import br.com.eric.springbootessentials.dto.AvaliacoesFisicasProjection;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import br.com.eric.springbootessentials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
public class AvaliacoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.create(avaliacaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes ()
    {
        return avaliacaoFisicaService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable (@PathVariable Integer page,
                                                                       @PathVariable Integer size)
    {
        return avaliacaoFisicaService.getAllAvaliacaoPageable(page, size);
    }
}
