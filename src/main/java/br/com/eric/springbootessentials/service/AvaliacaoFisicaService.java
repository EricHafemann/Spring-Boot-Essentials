package br.com.eric.springbootessentials.service;

import br.com.eric.springbootessentials.database.model.AlunosEntity;
import br.com.eric.springbootessentials.database.model.AvaliacoesFisicasEntity;
import br.com.eric.springbootessentials.database.repository.IAlunosRepository;
import br.com.eric.springbootessentials.database.repository.IAvaliacaoFisicaRepository;
import br.com.eric.springbootessentials.dto.AvaliacaoFisicaDto;
import br.com.eric.springbootessentials.dto.AvaliacoesFisicasProjection;
import br.com.eric.springbootessentials.exception.BadRequestException;
import br.com.eric.springbootessentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

    private final IAvaliacaoFisicaRepository avaliacaoFisicaRepository;
    private final IAlunosRepository alunosRepository;

    public void create(AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {
        AlunosEntity alunosEntity = alunosRepository.findById(avaliacaoFisicaDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacaoFisica =alunosEntity.getAvaliacoesFisicas();

        if(avaliacaoFisica != null)
        {
            throw new BadRequestException("Avaliação física já cadastrada para esse aluno !");
        }

        avaliacaoFisica = AvaliacoesFisicasEntity.builder()
                .peso(avaliacaoFisicaDto.getPeso())
                .altura(avaliacaoFisicaDto.getAltura())
                .porcentagemGorduraCorporal(avaliacaoFisicaDto.getPorcentagemGorduraCorporal())
                .build();

        alunosEntity.setAvaliacoesFisicas(avaliacaoFisica);
        // Cascada Salva a Avaliação automaticamente
        alunosRepository.save(alunosEntity);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes (){
        return avaliacaoFisicaRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacaoPageable (Integer page, Integer size)
    {
        return avaliacaoFisicaRepository.getAllAvaliacoesPage(PageRequest.of(page, size));
    }

}
