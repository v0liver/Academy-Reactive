package it.reactive.torneoDemo.Repository.Dao.SquadraDao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Repository.JpaRepository.GiocatoreJpaRepository;
import it.reactive.torneoDemo.Repository.JpaRepository.SquadraJpaRepository;
import it.reactive.torneoDemo.Repository.JpaRepository.TifoseriaJpaRepository;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.GiocatoreDuplicatoException;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.exception.TifoseriaGiaAssegnataException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class SquadraImpDaoJpaRepository implements SquadraDao {
    @Autowired
    SquadraJpaRepository squadraJpaRepository;
    @Autowired
    GiocatoreJpaRepository giocatoreJpaRepository;
    @Autowired
    TifoseriaJpaRepository tifoseriaJpaRepository;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        String nomeSquadra = squadraDTO.getNome();
        String coloriSociali = squadraDTO.getColoriSociali();

        if (squadraJpaRepository.findByNome(nomeSquadra).isPresent()) {
            throw new SquadraDuplicataException();

        }

        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(nomeSquadra);
        squadraModel.setColoriSociali(coloriSociali);

        try {

            SquadraModel savedSquadra = squadraJpaRepository.save(squadraModel);

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
        return giocatoreJpaRepository.findBySquadraModelIdSquadra(idSquadra);
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {

        return squadraJpaRepository.findAll();

//        }else {
//            List<Tuple> tuple = squadraJpaRepository.findAllWithoutGiocatori();
//            List<SquadraModel> squadraModels = new ArrayList<>();
//            for (Tuple tuple1 : tuple) {
//                SquadraModel squadraModel = new SquadraModel();
//                squadraModel.setIdSquadra(tuple1.get("idSquadra",Integer.class));
//                squadraModel.setNome(tuple1.get("nome",String.class));
//                squadraModel.setColoriSociali(tuple1.get("coloriSociali", String.class));
//                squadraModels.add(squadraModel);
//            }
//           return squadraModels;
    }


    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        Optional<SquadraModel> squadraModel = squadraJpaRepository.findById(idSquadra);
        if (squadraModel.isPresent()) {
            GiocatoreModel giocatoreModel = giocatoreJpaRepository.findByNomeCognome(giocatoreDto.getNomeCognome()).orElse(new GiocatoreModel());
            if (giocatoreModel.getNomeCognome() != null) {
                throw new GiocatoreDuplicatoException();
            }
            giocatoreModel.setSquadraModel(squadraModel.get());
            giocatoreModel.setNomeCognome(giocatoreDto.getNomeCognome());
            giocatoreJpaRepository.save(giocatoreModel);
            return squadraModel.get();
        } else throw new SquadraNonPresenteException();
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        Optional<SquadraModel> squadraModel = squadraJpaRepository.findById(idSquadra);

        TifoseriaModel tifoseriaModel =
                tifoseriaJpaRepository.findByNomeTifoseria(tifoseriaDTO.getNomeTifoseria()).orElse(new TifoseriaModel());
        SquadraModel squadraAssociata = tifoseriaModel.getSquadraModel();
        if (squadraAssociata != null && squadraAssociata.getIdSquadra() != null && squadraAssociata.getIdSquadra() != idSquadra) {
            throw new TifoseriaGiaAssegnataException();
        }

        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        tifoseriaModel.setSquadraModel(squadraModel.orElseThrow(() -> new SquadraNonPresenteException()));
        squadraModel.get().setTifoseria(tifoseriaJpaRepository.save(tifoseriaModel));


        return squadraModel.get();
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        Optional<SquadraModel> squadraModel = squadraJpaRepository.findById(idSquadra);
        if (!squadraModel.isPresent()) {
            throw new SquadraNonPresenteException();
        }
        giocatoreJpaRepository.deleteBySquadraModelIdSquadra(idSquadra);
        tifoseriaJpaRepository.deleteBySquadraModelIdSquadra(idSquadra);
        squadraJpaRepository.deleteById(idSquadra);
    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        SquadraModel squadraModel = getSquadraById(idSquadra);
        return squadraModel.getTifoseria();
    }
}
