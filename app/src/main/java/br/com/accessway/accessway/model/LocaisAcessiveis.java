package br.com.accessway.accessway.model;

import java.io.Serializable;

/**
 * Created by AccessWay.
 * Classe que será o apoio para pegar os locais acessíveis que serão cadastrados.
 */

public class LocaisAcessiveis implements Serializable{

    private Long id;
    private String local;
    private String descricao;
    private Double nota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Endereço: " + getLocal() + " - Descrição: " + getDescricao() + " Nota: " + getNota().toString();
    }
}
