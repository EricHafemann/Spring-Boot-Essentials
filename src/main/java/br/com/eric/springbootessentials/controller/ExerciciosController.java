package br.com.eric.springbootessentials.controller;

import br.com.eric.springbootessentials.database.model.ExerciciosEntity;
import br.com.eric.springbootessentials.dto.ExercicioDto;
import br.com.eric.springbootessentials.service.ExerciciosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
public class ExerciciosController {

    private final ExerciciosService exerciciosService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEntity> findAll ()
    {
        return exerciciosService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void craete (@Valid @RequestBody ExercicioDto exercicioDto)
    {
        exerciciosService.save(exercicioDto);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEntity> findAll (@PathVariable String grupoMuscular)
    {
        return exerciciosService.getExerciciosByGrupoMuscular(grupoMuscular);
    }
}
