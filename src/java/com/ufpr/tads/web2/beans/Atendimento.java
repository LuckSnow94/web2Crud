package com.ufpr.tads.web2.beans;

import java.util.Date;

public class Atendimento {

	private int idAtendimento;
	private Date dataHoraAtendimento;
	private String descricaoAtendimento;
	private Produto produto;
	private TipoAtendimento tipoAtendimento;
	private Usuario usuario;
	private Cliente cliente;
	private String resultadoAtendimento;
	
	public int getIdAtendimento() {
		return idAtendimento;
	}
	public void setIdAtendimento(int idAtendimento) {
		this.idAtendimento = idAtendimento;
	}
	public Date getDataHoraAtendimento() {
		return dataHoraAtendimento;
	}
	public void setDataHoraAtendimento(Date dataHoraAtendimento) {
		this.dataHoraAtendimento = dataHoraAtendimento;
	}
	public String getDescricaoAtendimento() {
		return descricaoAtendimento;
	}
	public void setDescricaoAtendimento(String descricaoAtendimento) {
		this.descricaoAtendimento = descricaoAtendimento;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public void setUsuario(LoginBean loginBean) {
		Usuario u = new Usuario();
		u.setId(loginBean.getId());
		u.setNome(loginBean.getNome());
		this.usuario = u;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getResultadoAtendimento() {
		return resultadoAtendimento;
	}
	public void setResultadoAtendimento(String resultadoAtendimento) {
		this.resultadoAtendimento = resultadoAtendimento;
	}
	
	
}
