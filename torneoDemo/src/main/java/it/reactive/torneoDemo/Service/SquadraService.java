package it.reactive.torneoDemo.Service;

import it.reactive.torneoDemo.Repository.Dao.SquadraDao;
import it.reactive.torneoDemo.DTO.squadra.SquadraDTO;
import it.reactive.torneoDemo.Repository.Mapper.SquadraMapper;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.resource.SquadraResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SquadraService {
    @Autowired
    SquadraDao squadraDao;

    public SquadraResource salvaSquadra(SquadraDTO squadraDTO) {

        SquadraModel squadraModel = squadraDao.salvaSquadra(squadraDTO);

        return SquadraMapper.fromModelToResource(squadraModel);
    }
}
