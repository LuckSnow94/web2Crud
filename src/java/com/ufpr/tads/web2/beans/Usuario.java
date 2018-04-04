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
public class Usuario implements Serializable{
    private int id;
    private String login;
    private String senha;
    private String nome;

    public Usuario() {
    }

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getId(){
        return id;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }

    public String getNome(){
        return nome;
    }

}
