package br.com.trm.categorizadorautomaticodedespesas.sort;

import br.com.trm.categorizadorautomaticodedespesas.MovimentacaoDeRegistro;
import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;

public class CalculadoraMovimentacaoRegistro {

	public MovimentacaoDeRegistro calculaMovimentacao(RegistroEsperado registroProcurado,
			RegistroJaLancado registroEncontrado) {
		if (registroEncontrado.getPosicaoLancada() != registroProcurado.getPosicaoEsperada()) {
			return new MovimentacaoDeRegistro(registroEncontrado.getPosicaoLancada(),
					registroProcurado.getPosicaoEsperada());
		} else {
			return null;
		}
	}

}
