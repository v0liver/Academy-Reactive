package it.reactive.torneoDemo.Repository.JpaRepository;

import it.reactive.torneoDemo.model.GiocatoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiocatoreJpaRepository extends JpaRepository<GiocatoreModel,Integer> {
}
