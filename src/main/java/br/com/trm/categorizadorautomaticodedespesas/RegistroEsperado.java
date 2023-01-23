package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;

public class RegistroEsperado extends Registro {

	protected int posicaoEsperada;

	public RegistroEsperado(String descricao, BigDecimal valor, String formaDePagamento, Integer ocorrencia,
			int posicaoEsperada) {
		super(descricao, valor, formaDePagamento, ocorrencia);
		this.posicaoEsperada = posicaoEsperada;
	}

	public int getPosicaoEsperada() {
		return posicaoEsperada;
	}

	public void setPosicaoEsperada(int posicaoEsperada) {
		this.posicaoEsperada = posicaoEsperada;
	}

	@Override
	public String toString() {
		return "RegistroEsperado [posicaoEsperada=" + posicaoEsperada + ", descricao=" + descricao + ", valor=" + valor
				+ ", formaDePagamento=" + formaDePagamento + ", ocorrencia=" + ocorrencia + "]";
	}

}
