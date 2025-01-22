package it.reactive.demoTorneoSpringBatch.DTO;

import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class SquadraTorneoDTO extends TipoFile {
    String nomeSquadra;
    String nomeTorneo;
    Integer idSquadra;
    Integer idTorneo;

    public Integer getIdSquadra() {
        return idSquadra;
    }

    public void setIdSquadra(Integer idSquadra) {
        this.idSquadra = idSquadra;
    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

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
