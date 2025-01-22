package it.reactive.demoTorneoSpringBatch.DTO;


import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class GiocatoreDto extends TipoFile {


    private Integer id;
    private String nomeCognome;
    private String nomeSquadra;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNomeSquadra() {
        return nomeSquadra;
    }

    public void setNomeSquadra(String nomeSquadra) {
        this.nomeSquadra = nomeSquadra;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public GiocatoreDto() {
    }
}
