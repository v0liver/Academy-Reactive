package it.reactive.torneoDemo.Repository.Dao.SquadraDao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.*;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class SquadraImpDaoJpaEntityManagerBase implements SquadraDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        if (!entityManager.createNamedQuery("SquadraModel.findByNome", SquadraModel.class)
                .setParameter("nome", squadraDTO.getNome()).getResultList().isEmpty()) {
            throw new SquadraDuplicataException();
        } else {
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setNome(squadraDTO.getNome());
            squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
            entityManager.persist(squadraModel);

            return squadraModel;

        }
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        return entityManager.find(SquadraModel.class, idSquadra);
    }

    @Override
    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {

        return new HashSet<GiocatoreModel>(entityManager.createNamedQuery("GiocatoreModel.findByIdSquadra",
                        GiocatoreModel.class)
                .setParameter("idSquadra", idSquadra)
                .getResultList());
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        TypedQuery<SquadraModel> typedQuery = entityManager.createQuery("Select s From SquadraModel s",
                SquadraModel.class);

        return typedQuery.getResultList();
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        if (getSquadraById(idSquadra) != null) {
            if (!entityManager.createNamedQuery("GiocatoreModel.findByNomeCognome", GiocatoreModel.class)
                    .setParameter("nomeCognomeGiocatore", giocatoreDto.getNomeCognome()).getResultList().isEmpty()) {
                throw new GiocatoreDuplicatoException();
            } else {
                GiocatoreModel giocatoreModel = new GiocatoreModel();
                giocatoreModel.setNomeCognome(giocatoreDto.getNomeCognome());
                giocatoreModel.setSquadraModel(getSquadraById(idSquadra));
                entityManager.persist(giocatoreModel);
                return getSquadraById(idSquadra);
            }
        } else throw new SquadraNonPresenteException();

    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        try {
            TifoseriaModel tifoseriaModel = getTifoseriaBySquadraId(idSquadra);

            if (tifoseriaModel != null) {
                tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
                entityManager.merge(tifoseriaModel);
            } else {

                tifoseriaModel = new TifoseriaModel();
                tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
                tifoseriaModel.setSquadraModel(getSquadraById(idSquadra));
                entityManager.persist(tifoseriaModel);
                SquadraModel squadraModel = getSquadraById(idSquadra);
                entityManager.refresh(squadraModel);//senza refresh non funziona il persist e ti restituisce l oggtto
                // vecchio non aggiornato
            }
            return getSquadraById(idSquadra);
        } catch (PersistenceException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new TifoseriaGiaAssegnataException();
            } else {
                throw new RuntimeException("Problema nell' aggiunta della tifoseria");
            }

        }

    }


    @Override
    public void rimuoviSquadra(int idSquadra) {
        SquadraModel squadraModel = getSquadraById(idSquadra);
        if (squadraModel == null) {
            throw new SquadraNonPresenteException();
        }
        if (squadraModel.getTifoseria() != null) {
            entityManager.remove(squadraModel.getTifoseria());
        }
        if (squadraModel.getGiocatori() != null) {
            for (GiocatoreModel giocatoreModel : squadraModel.getGiocatori()) {
                entityManager.remove(giocatoreModel);
            }
        }
        entityManager.remove(squadraModel);


    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        List<TifoseriaModel> squadraModels = (List<TifoseriaModel>) entityManager.createNamedQuery("TifoseriaModel.findByIdSquadra",
                        TifoseriaModel.class)
                .setParameter("idSquadra", idSquadra)
                .getResultList();
        if (squadraModels.isEmpty()) {
            return null;
        }
        return squadraModels.get(0);
    }
}
