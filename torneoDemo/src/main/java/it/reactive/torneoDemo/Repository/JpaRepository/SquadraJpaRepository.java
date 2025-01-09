package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SquadraJpaRepository extends JpaRepository<SquadraModel,Integer> {
    Optional<SquadraModel> findByNome(String nome);
}
