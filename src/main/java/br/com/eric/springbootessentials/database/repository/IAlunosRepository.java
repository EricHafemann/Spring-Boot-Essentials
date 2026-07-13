package br.com.eric.springbootessentials.database.repository;

import br.com.eric.springbootessentials.database.model.AlunosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail (String email);

    @Query(value = "SELECT a FROM AlunosEntity a JOIN FETCH a.avaliacoesFisicas WHERE a.id = :alunoId")
    Optional<AlunosEntity> findByIdFetch(Integer alunoId);
}
