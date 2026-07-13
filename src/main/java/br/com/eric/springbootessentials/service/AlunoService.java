package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.database.model.AlunosEntity;
import br.com.eric.springbootessentials.database.model.AvaliacoesFisicasEntity;
import br.com.eric.springbootessentials.database.model.TreinosEntity;
import br.com.eric.springbootessentials.database.repository.IAlunosRepository;
import br.com.eric.springbootessentials.database.repository.IAvaliacaoFisicaRepository;
import br.com.eric.springbootessentials.database.repository.ITreinosRepository;
import br.com.eric.springbootessentials.dto.AlunoDto;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final ITreinosRepository treinosRepository;
    private final IAvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final IAlunosRepository alunosRepository;

    public void create (AlunoDto alunoDto) throws BadRequestException {

        AlunosEntity aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if(aluno != null)
        {
            throw new BadRequestException("Aluno já cadastrado com esse email");
        }

        AlunosEntity alunosEntity = AlunosEntity.builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .build();

        alunosRepository.save(alunosEntity);
    }

    public AvaliacoesFisicasEntity getAlunoAvaliacao (Integer id) throws NotFoundException
    {
        AlunosEntity alunosEntity = alunosRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aluno não exite com esse Id"));

        AvaliacoesFisicasEntity avaliacao = alunosEntity.getAvaliacoesFisicas();

        if(avaliacao == null)
        {
            throw new NotFoundException("Aluno não possui avaliação");
        }

        return avaliacao;

    }

    @Transactional
    public void delete (Integer alunoId) throws NotFoundException
    {
        AlunosEntity aluno = alunosRepository.findById(alunoId)
                        .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        // Deletar Treino
        List<Integer> treinosIds =  aluno.getTreinosEntitySet().stream()
                .map(TreinosEntity::getId)
                        .toList();

        treinosRepository.deleteAllById(treinosIds);

        //Deletar Aluno
        alunosRepository.deleteById(alunoId);

        //Deletar Avaliacao
        avaliacaoFisicaRepository.deleteById(aluno.getAvaliacoesFisicas().getId());
    }
}
