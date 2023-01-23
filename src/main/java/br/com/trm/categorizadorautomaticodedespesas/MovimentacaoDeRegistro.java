package br.com.trm.categorizadorautomaticodedespesas;

public class MovimentacaoDeRegistro {
	private int daPosicao;
	private int paraPosicao;

	public MovimentacaoDeRegistro(int daPosicao, int paraPosicao) {
		this.daPosicao = daPosicao;
		this.paraPosicao = paraPosicao;
	}

	public int getDaPosicao() {
		return daPosicao;
	}

	public int getParaPosicao() {
		return paraPosicao;
	}

}
