/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.io.Serializable;

/**
 *
 * @author luck
 */
public class Cidade implements Serializable{
    
    private int idCidade;
    private int idEstado;
    private String nomeCidade;

    public Cidade(int idCidade, int idEstado, String nomeCidade) {
        this.idCidade = idCidade;
        this.idEstado = idEstado;
        this.nomeCidade = nomeCidade;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
}
