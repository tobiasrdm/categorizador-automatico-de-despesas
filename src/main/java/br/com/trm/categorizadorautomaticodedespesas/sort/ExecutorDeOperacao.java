package br.com.trm.categorizadorautomaticodedespesas.sort;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;

public interface ExecutorDeOperacao {
	
	public void executa(RegistroEsperado registroEsperado, OperacaoSobreRegistro operacao);

}
