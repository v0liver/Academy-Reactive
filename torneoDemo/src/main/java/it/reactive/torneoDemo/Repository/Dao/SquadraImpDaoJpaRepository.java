package it.reactive.torneoDemo.Repository.Dao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Repository.JpaRepository.SquadraJpaRepository;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class SquadraImpDaoJpaRepository implements SquadraDao{
    @Autowired
    SquadraJpaRepository squadraJpaRepository;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();

        if (squadraJpaRepository.findByNome(nomeSquadra).isPresent()){
            throw new SquadraDuplicataException();

        }
        // Creiamo un oggetto SquadraModel e lo popoliamo con i dati
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(nomeSquadra);
        squadraModel.setColoriSociali(coloriSociali);

        try {
            // Salviamo l'oggetto SquadraModel usando il repository JPA
            SquadraModel savedSquadra = squadraJpaRepository.save(squadraModel);

            // Restituiamo il modello della squadra appena salvata
            return savedSquadra;

        } catch (Exception e) {

                throw new RuntimeException("Errore durante il salvataggio della squadra.", e);
        }
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        return squadraJpaRepository.findById(idSquadra)
                .orElseThrow(() -> new SquadraNonPresenteException());
    }

    @Override
    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {

        return Collections.emptySet();
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        return Collections.emptyList();
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        return null;
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        return null;
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {

    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        return null;
    }
}
