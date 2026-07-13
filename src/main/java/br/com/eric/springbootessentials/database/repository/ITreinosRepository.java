package br.com.eric.springbootessentials.database.repository;

import br.com.eric.springbootessentials.database.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITreinosRepository extends JpaRepository<TreinosEntity, Integer> {

    Optional<TreinosEntity> findByNomeAndAlunoId (String nome, Integer id);
}
