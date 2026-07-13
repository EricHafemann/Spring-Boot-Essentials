package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.database.model.AlunosEntity;
import br.com.eric.springbootessentials.database.model.ExerciciosEntity;
import br.com.eric.springbootessentials.database.model.TreinosEntity;
import br.com.eric.springbootessentials.database.repository.IAlunosRepository;
import br.com.eric.springbootessentials.database.repository.IExerciciosRepository;
import br.com.eric.springbootessentials.database.repository.ITreinosRepository;
import br.com.eric.springbootessentials.dto.TreinoDto;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final IExerciciosRepository exerciciosRepository;
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;

    public void create(TreinoDto treinoDto) throws NotFoundException, BadRequestException {
        Set<ExerciciosEntity> exercicios =new HashSet<>();

        AlunosEntity aluno = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEntity treino= treinosRepository.findByNomeAndAlunoId(
                treinoDto.getNome(),
                treinoDto.getAlunoId())
                .orElse(null);

        if(treino != null)
        {
            throw new BadRequestException("Já existe um treino com esse nome para esse aluno");
        }

        for (Integer exercicioId : treinoDto.getExerciciosIds())
        {
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException("Exercicio com o ID: "+exercicioId+", não foi encontrado"));

            exercicios.add(exercicio);
        }

        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }

}
