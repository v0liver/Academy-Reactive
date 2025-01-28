package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.Repository.JpaRepository.TorneoJpaRepository;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.exception.TorneoConPiuSquadreException;
import it.reactive.torneoDemo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class TorneoImpDaoJpaRepository  implements TorneoDao{
    @Autowired
    private TorneoJpaRepository torneoJpaRepository;

    @Autowired
    private EntityManager entityManager;

    @Override

    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        return torneoJpaRepository.save(torneoModel);
    }

    @Override

    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        TorneoModel torneo = torneoJpaRepository.findById(idTorneo).orElseThrow(()->new TorneoNonTrovatoException());
        SquadraModel squadra = entityManager.find(SquadraModel.class, idSquadra);
        if (squadra == null) {
            throw new SquadraNonPresenteException();
        }

        if (torneo.getSquadre().contains(squadra)) {
            throw new SquadraDuplicataException();
        }

        torneo.getSquadre().add(squadra);
        return torneoJpaRepository.save(torneo);
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        return torneoJpaRepository.findAll();
    }

    @Override

    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        TorneoModel torneo = torneoJpaRepository.findById(idTorneo).orElseThrow(TorneoNonTrovatoException::new);

        if (!torneo.getSquadre().isEmpty()) {
            throw new TorneoConPiuSquadreException();
        }

        torneoJpaRepository.delete(torneo);
        return torneoJpaRepository.findAll();
    }

    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        return torneoJpaRepository.findById(idTorneo).orElseThrow(TorneoNonTrovatoException::new);
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        String query = "select s from squadraModel s join s.tornei t where t.id = :idTorneo";
        TypedQuery<SquadraModel> typedQuery = entityManager.createQuery(query, SquadraModel.class);
        typedQuery.setParameter("idTorneo", idTorneo);
        return typedQuery.getResultList();
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        String query = "select t from torneoModel t join t.squadre s where s.id = :idSquadra";
        TypedQuery<TorneoModel> typedQuery = entityManager.createQuery(query, TorneoModel.class);
        typedQuery.setParameter("idSquadra", idSquadra);
        return typedQuery.getResultList();
    }
}
