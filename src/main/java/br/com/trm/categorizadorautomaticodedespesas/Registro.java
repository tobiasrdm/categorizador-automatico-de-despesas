package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;

public class Registro {
	protected String descricao;
	protected BigDecimal valor;
	protected String formaDePagamento;
	protected Integer ocorrencia;

	public Registro(String descricao, BigDecimal valor, String formaDePagamento, Integer ocorrencia) {
		this.descricao = descricao;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
		this.ocorrencia = ocorrencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public Integer getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Integer ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

}
