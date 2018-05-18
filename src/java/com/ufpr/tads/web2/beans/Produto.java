package com.ufpr.tads.web2.beans;

public class Produto {

	private int idProduto;
	private String nomeProduto;
	
	public Produto() {
		super();
	}
	
	public Produto(int idProduto, String nomeProduto) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
	}
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	
}
