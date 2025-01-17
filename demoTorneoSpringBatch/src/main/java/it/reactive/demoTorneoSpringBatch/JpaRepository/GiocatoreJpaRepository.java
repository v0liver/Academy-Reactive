package it.reactive.demoTorneoSpringBatch.JpaRepository;


import it.reactive.demoTorneoSpringBatch.model.GiocatoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface GiocatoreJpaRepository extends JpaRepository<GiocatoreModel, Integer> {
    Set<GiocatoreModel> findBySquadraModelIdSquadra(Integer idSquadra);

    void deleteBySquadraModelIdSquadra(Integer idSquadra);

    Optional<GiocatoreModel> findByNomeCognome(String nomeCognome);
}
