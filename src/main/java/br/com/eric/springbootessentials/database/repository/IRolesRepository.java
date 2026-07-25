package br.com.eric.springbootessentials.database.repository;

import br.com.eric.springbootessentials.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import javax.swing.text.html.Option;
import java.util.Optional;

public interface IRolesRepository extends JpaRepository<RolesEntity, Integer> {

    Optional<RolesEntity> findByNome (String role);
}
