package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TifoseriaJpaRepository extends JpaRepository<TifoseriaModel, Integer> {
    Optional<TifoseriaModel> findByNomeTifoseria(String nomeTifoseria);

    void deleteBySquadraModelIdSquadra(Integer idSquadra);
}
