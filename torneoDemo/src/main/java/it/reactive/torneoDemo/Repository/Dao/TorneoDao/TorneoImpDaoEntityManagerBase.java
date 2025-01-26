package it.reactive.torneoDemo.Repository.Dao.TorneoDao;

import it.reactive.torneoDemo.DTO.torneo.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.exception.*;
import it.reactive.torneoDemo.Repository.Dao.SquadraDao.SquadraDao;
import it.reactive.torneoDemo.Utility.Costanti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TorneoImpDaoEntityManagerBase implements TorneoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SquadraDao squadraDao;

    @Override
    public TorneoModel aggiungiTorneo(TorneoDTO torneoDTO) {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        entityManager.persist(torneoModel);
        return torneoModel;
    }

    @Override
    public TorneoModel censitaSquadraAlTorneo(Integer idTorneo, Integer idSquadra) {
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, idTorneo);
        SquadraModel squadraModel = squadraDao.getSquadraById(idSquadra);
        torneoModel.getSquadre().add(squadraModel);
        squadraModel.getTornei().add(torneoModel);
        entityManager.merge(torneoModel);
        entityManager.merge(squadraModel);
        return torneoModel;
    }

    @Override
    public List<TorneoModel> getTorneoEndSquadre() {
        return entityManager.createQuery("from torneoModel t left join t.squadre ", TorneoModel.class).getResultList();
    }

    @Override
    @Transactional
    public List<TorneoModel> eliminaTorneoConSquadreAndGiocatori(int idTorneo) {
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, idTorneo);
        if (torneoModel == null) {
            throw new TorneoNonTrovatoException();
        }

        if (torneoModel.getSquadre().size() > 0) {
            throw new TorneoConPiuSquadreException();
        }

        entityManager.remove(torneoModel);
        return getTorneoEndSquadre();
    }

    @Override
    public TorneoModel getTorneoById(Integer idTorneo) {
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, idTorneo);
        if (torneoModel == null) {
            throw new TorneoNonTrovatoException();
        }
        return torneoModel;
    }

    @Override
    public List<SquadraModel> getSquadreByIdTorneo(Integer idTorneo) {
        return entityManager.createQuery("select s from squadraModel s join s.tornei t where t.idTorneo = :idTorneo", SquadraModel.class)
                .setParameter("idTorneo", idTorneo)
                .getResultList();
    }

    @Override
    public List<TorneoModel> getTorneiByIdSquadra(Integer idSquadra) {
        return entityManager.createQuery("select t from torneoModel t join t.squadre s where s.idSquadra = :idSquadra", TorneoModel.class)
                .setParameter("idSquadra", idSquadra)
                .getResultList();
    }
}
