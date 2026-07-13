package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.dto.TreinoDto;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import br.com.eric.springbootessentials.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
public class TreinoController {

    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create (@Valid   @RequestBody TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        treinoService.create(treinoDto);
    }
}
