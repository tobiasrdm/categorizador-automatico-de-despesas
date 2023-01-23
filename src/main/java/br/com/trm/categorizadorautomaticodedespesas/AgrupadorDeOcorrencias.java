package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;

public class AgrupadorDeOcorrencias {

	private String descricaoProcurada;
	private BigDecimal valor;
	private String formaDePagamento;

	public AgrupadorDeOcorrencias(String descricaoProcurada, BigDecimal valor, String formaDePagamento) {
		this.descricaoProcurada = descricaoProcurada;
		this.valor = valor;
		this.formaDePagamento = formaDePagamento;
	}

	public String getDescricaoProcurada() {
		return descricaoProcurada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoProcurada == null) ? 0 : descricaoProcurada.hashCode());
		result = prime * result + ((formaDePagamento == null) ? 0 : formaDePagamento.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgrupadorDeOcorrencias other = (AgrupadorDeOcorrencias) obj;
		if (descricaoProcurada == null) {
			if (other.descricaoProcurada != null)
				return false;
		} else if (!descricaoProcurada.equals(other.descricaoProcurada))
			return false;
		if (formaDePagamento == null) {
			if (other.formaDePagamento != null)
				return false;
		} else if (!formaDePagamento.equals(other.formaDePagamento))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgrupadorDeOcorrencias [descricaoProcurada=" + descricaoProcurada + ", valor=" + valor
				+ ", formaDePagamento=" + formaDePagamento + "]";
	}

}
