package it.reactive.torneoDemoMongo.Mapper;


import it.reactive.torneoDemoMongo.DTO.tifoseria.TifoseriaDTO;
import it.reactive.torneoDemoMongo.model.TifoseriaModel;
import it.reactive.torneoDemoMongo.resource.TifoseriaResource;
import org.springframework.stereotype.Component;

@Component
public class TifoseriaMapper {
    public TifoseriaResource fromModelToResource(TifoseriaModel tifoseriaModel) {
        TifoseriaResource tifoseriaResource = new TifoseriaResource();
        tifoseriaResource.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        return tifoseriaResource;
    }

    public TifoseriaModel fromDtoToModel(TifoseriaDTO tifoseriaDTO) {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return tifoseriaModel;
    }
}
