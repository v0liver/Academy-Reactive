package it.reactive.torneoDemo.Mapper;

import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.resource.TifoseriaResource;
import org.springframework.stereotype.Component;

@Component
public class TifoseriaMapper {
    public TifoseriaResource fromModelToResource(TifoseriaModel tifoseriaModel) {
        TifoseriaResource tifoseriaResource = new TifoseriaResource();
        tifoseriaResource.setIdTifoseria(tifoseriaModel.getIdTifoseria());
        tifoseriaResource.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        return tifoseriaResource;
    }
}
