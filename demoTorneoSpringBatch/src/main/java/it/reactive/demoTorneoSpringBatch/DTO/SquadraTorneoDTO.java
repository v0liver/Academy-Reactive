package it.reactive.demoTorneoSpringBatch.DTO;

import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class SquadraTorneoDTO extends TipoFile {
    String nomeSquadra;
    String nomeTorneo;

    public String getNomeTorneo()  {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }


}
