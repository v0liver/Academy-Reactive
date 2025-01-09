package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GiocatoreJpaRepository extends JpaRepository<GiocatoreModel,Integer> {
    Set<GiocatoreModel> findBySquadraModelIdSquadra(Integer idSquadra);
}
