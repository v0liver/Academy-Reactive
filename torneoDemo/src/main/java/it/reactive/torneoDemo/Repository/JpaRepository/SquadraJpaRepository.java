package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

public interface SquadraJpaRepository extends JpaRepository<SquadraModel,Integer> {
    Optional<SquadraModel> findByNome(String nome);
    //@Query("Select s from SquadraModel s")
    //List<SquadraModel> findAllWithoutGiocatori();
//    @Query("SELECT new SquadraDTO(s.idSquadra, s.nome, s.coloriSociali) FROM SquadraModel s")
//    List<SquadraDTO> findAllWithoutGiocatori();
     @Query("SELECT s.idSquadra as idSquadra, s.nome as nome, s.coloriSociali as coloriSociali FROM SquadraModel s")
    List<Tuple> findAllWithoutGiocatori();
}
