package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.database.model.ExerciciosEntity;
import br.com.eric.springbootessentials.database.repository.IExerciciosRepository;
import br.com.eric.springbootessentials.dto.ExercicioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciciosService {

    private final IExerciciosRepository exerciciosRepository;

    public List<ExerciciosEntity> findAll ()
    {
        return exerciciosRepository.findAll();
    }

    public void save (ExercicioDto exercicio)
    {
        ExerciciosEntity exerciciosEntity = ExerciciosEntity.builder()
                .nome(exercicio.getNome())
                .grupoMuscular(exercicio.getGrupoMuscular())
                .build();

        exerciciosRepository.save(exerciciosEntity);
    }

    public List<ExerciciosEntity> getExerciciosByGrupoMuscular (String grupo)
    {
        return exerciciosRepository.findAllByGrupoMuscular(grupo);
    }
}
