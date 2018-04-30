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
public class Estado implements Serializable{
    
    private int idEstado;
    private String siglaEstado;
    private String nomeEstado;

    public Estado(int idEstado, String siglaEstado, String nomeEstado) {
        this.idEstado = idEstado;
        this.siglaEstado = siglaEstado;
        this.nomeEstado = nomeEstado;
    }
        
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
    
}
