package it.reactive.torneoDemo.Repository.Mapper;

import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.resource.TifoseriaResource;

public class TifoseriaMapper {
    public static TifoseriaResource fromModelToResource(TifoseriaModel tifoseriaModel) {
        TifoseriaResource tifoseriaResource = new TifoseriaResource();
        tifoseriaResource.setIdTifoseria(tifoseriaModel.getIdTifoseria());
        tifoseriaResource.setNomeTifoseria(tifoseriaModel.getNomeTifoseria());
        return tifoseriaResource;
    }
}
