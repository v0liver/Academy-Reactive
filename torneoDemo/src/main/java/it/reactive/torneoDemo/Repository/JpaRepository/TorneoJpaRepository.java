package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TorneoJpaRepository extends JpaRepository<TorneoModel, Integer> {

}
