/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author luck
 */
public class Cliente implements Serializable{
    private int idCliente;
    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;
    private Date dataCliente;
    private String ruaCliente;
    private int nrCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String ufCliente;

    public Cliente(){
    }
    
    public Cliente(int idCliente, String cpfCliente, String nomeCliente, String emailCliente) {
        this.idCliente = idCliente;
        this.cpfCliente = cpfCliente.substring(0,3) + "." + cpfCliente.substring(3, 6) + "." + cpfCliente.substring(6, 9) + "-" + cpfCliente.substring(9, 11);
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
    }

    public Cliente(int idCliente, String cpfCliente, String nomeCliente, String emailCliente, Date dataCliente, String ruaCliente, int nrCliente, String cepCliente, String cidadeCliente, String ufCliente) {
        this.idCliente = idCliente;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.dataCliente = dataCliente;
        this.ruaCliente = ruaCliente;
        this.nrCliente = nrCliente;
        this.cepCliente = cepCliente;
        this.cidadeCliente = cidadeCliente;
        this.ufCliente = ufCliente;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Date getDateCliente(){
        return dataCliente;
    }

    public String getDataCliente() {
        //Formatando data do bean
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
        String data = fmt.format(this.dataCliente);
        return data;
    }

    public void setDataCliente(Date dataCliente) {
        this.dataCliente = dataCliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }

    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public int getNrCliente() {
        return nrCliente;
    }

    public void setNrCliente(int nrCliente) {
        this.nrCliente = nrCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getUfCliente() {
        return ufCliente;
    }

    public void setUfCliente(String ufCliente) {
        this.ufCliente = ufCliente;
    }
    
}
