package it.reactive.torneoDemo.Repository.Dao.SquadraDao;

import it.reactive.torneoDemo.DTO.giocatore.GiocatoreDto;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemo.Utility.Costanti;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoreModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_QUERY)
public class SquadraImpDaoJpaEntityManagerQuery implements SquadraDao{
    @Autowired
    EntityManager entityManager;

    @Override
    public SquadraModel salvaSquadra(SquadraDTO squadraDTO) {
        if (entityManager.createNamedQuery("SquadraModel.findByNome",SquadraModel.class)
                .setParameter("nomeSquadra",squadraDTO.getNome())!=null){
            throw new SquadraDuplicataException();
        }
        String sql = "insert into squadra (nome,colori_sociali) values (:nome,:coloriSociali) Returning id";

       int idSquadra= (Integer) entityManager.createNativeQuery(sql)
                .setParameter("nome",squadraDTO.getNome())
                .setParameter("coloriSociali",squadraDTO.getColoriSociali())
                .getSingleResult();

        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(squadraDTO.getNome());
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        squadraModel.setIdSquadra(idSquadra);

        return squadraModel;
    }

    @Override
    public SquadraModel getSquadraById(int idSquadra) {
        return entityManager.createNamedQuery("SquadraModel.findById",SquadraModel.class)
                .setParameter("id",idSquadra)
                .getSingleResult();
    }

    @Override
    public Set<GiocatoreModel> getGiocatoriBySquadraId(int idSquadra) {

        return new HashSet<GiocatoreModel>(entityManager.createNamedQuery("GiocatoreModel.findByIdSquadra",
                        GiocatoreModel.class)
                .setParameter("idSquadra",idSquadra)
                .getResultList());
    }

    @Override
    public List<SquadraModel> ricercaSquadra(boolean completo) {
        TypedQuery<SquadraModel> typedQuery =  entityManager.createQuery("Select s From SquadraModel s",
                SquadraModel.class);

        return  typedQuery.getResultList();
    }

    @Override
    public SquadraModel aggiungiGiocatore(int idSquadra, GiocatoreDto giocatoreDto) {
        String sql = "insert into giocatore (nome_cognome,id_squadra) values (:nomeCognome,:idSquadra)";
        entityManager.createNativeQuery(sql)
                .setParameter("nomeCognome",giocatoreDto.getNomeCognome())
                .setParameter("idSquadra",idSquadra)
                .executeUpdate();

        return getSquadraById(idSquadra);
    }

    @Override
    public SquadraModel aggiungiTifoseria(int idSquadra, TifoseriaDTO tifoseriaDTO) {
        if (getTifoseriaBySquadraId(idSquadra)==null) {
            String sql = "Insert into tifoseria (nome_tifoseria,id_squadra) values (:nomeTifoseria,:idSquadra)";
            entityManager.createNativeQuery(sql)
                    .setParameter("nomeTifoseria", tifoseriaDTO.getNomeTifoseria())
                    .setParameter("idSquadra", idSquadra)
                    .executeUpdate();
        }else {
            String jpql = "Update  TifoseriaModel t  Set t.nomeTifoseria=:nomeTifoseria where id_squadra= :idSquadra";
            entityManager.createQuery(jpql)
                    .setParameter("nomeTifoseria", tifoseriaDTO.getNomeTifoseria())
                    .setParameter("idSquadra", idSquadra)
                    .executeUpdate();
        }
        return getSquadraById(idSquadra);
    }

    @Override
    public void rimuoviSquadra(int idSquadra) {
        SquadraModel squadraModel = getSquadraById(idSquadra);
        if (squadraModel==null){
            throw new SquadraNonPresenteException();
        }
        if (squadraModel.getTifoseria()!=null) {
            entityManager.remove(squadraModel.getTifoseria());
        }
        if (squadraModel.getGiocatori()!=null) {
            for (GiocatoreModel giocatoreModel : squadraModel.getGiocatori()) {
                entityManager.remove(giocatoreModel);
            }
        }
        entityManager.remove(squadraModel);


    }

    @Override
    public TifoseriaModel getTifoseriaBySquadraId(int idSquadra) {
        return entityManager.createNamedQuery("TifoseriaModel.findByIdSquadra",TifoseriaModel.class)
                .setParameter("idSquadra",idSquadra)
                .getSingleResult();
    }
}
