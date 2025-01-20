package it.reactive.demoTorneoSpringBatch.DTO;


import it.reactive.demoTorneoSpringBatch.model.TipoFile;

public class SquadraDTO extends TipoFile {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String nome;

    private String coloriSociali;

    private String tifoseria;

    public String getTifoseria() {
        return tifoseria;
    }

    public void setTifoseria(String tifoseria) {
        this.tifoseria = tifoseria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }
}
