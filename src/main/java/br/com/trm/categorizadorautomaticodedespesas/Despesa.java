package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;

public class Despesa {
	private String descricao;
	private String categoria;
	private BigDecimal valor;
	private Integer diaDoMes;
	private String formaDePagamento;
	private String realizado;
	
    public Despesa() {

    }

    public Despesa(String descricao, String categoria, BigDecimal valor, Integer diaDoMes, String formaDePagamento, String realizado) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.diaDoMes = diaDoMes;
        this.formaDePagamento = formaDePagamento;
        this.realizado = realizado;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getDiaDoMes() {
		return diaDoMes;
	}

	public void setDiaDoMes(Integer diaDoMes) {
		this.diaDoMes = diaDoMes;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public String getRealizado() {
		return realizado;
	}

	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

}
