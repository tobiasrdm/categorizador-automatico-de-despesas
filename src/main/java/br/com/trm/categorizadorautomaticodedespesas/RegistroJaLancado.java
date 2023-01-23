package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;

public class RegistroJaLancado extends Registro {

	protected int posicaoLancada;

	public RegistroJaLancado(String descricao, BigDecimal valor, String formaDePagamento, Integer ocorrencia,
			int posicaoLancada) {
		super(descricao, valor, formaDePagamento, ocorrencia);
		this.posicaoLancada = posicaoLancada;
	}

	public int getPosicaoLancada() {
		return posicaoLancada;
	}

	public void setPosicaoLancada(int posicaoLancada) {
		this.posicaoLancada = posicaoLancada;
	}

	@Override
	public String toString() {
		return "RegistroJaLancado [posicaoLancada=" + posicaoLancada + ", descricao=" + descricao + ", valor=" + valor
				+ ", formaDePagamento=" + formaDePagamento + ", ocorrencia=" + ocorrencia + "]";
	}

}
