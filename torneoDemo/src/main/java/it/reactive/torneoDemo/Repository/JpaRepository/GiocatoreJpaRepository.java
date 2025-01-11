package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

public interface GiocatoreJpaRepository extends JpaRepository<GiocatoreModel,Integer> {
    Set<GiocatoreModel> findBySquadraModelIdSquadra(Integer idSquadra);
    void deleteBySquadraModelIdSquadra(Integer idSquadra);

   Optional <GiocatoreModel> findByNomeCognome(String nomeCognome);
}
