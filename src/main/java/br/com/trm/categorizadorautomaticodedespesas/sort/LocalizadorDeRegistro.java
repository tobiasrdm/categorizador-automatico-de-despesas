package br.com.trm.categorizadorautomaticodedespesas.sort;

import java.util.List;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;

public class LocalizadorDeRegistro {

	public RegistroJaLancado localizaRegistro(RegistroEsperado registroProcurado, List<RegistroJaLancado> registrosJaLancados) {
		for (int i = 0; i < registrosJaLancados.size(); i++) {
			RegistroJaLancado registroJaLancado = registrosJaLancados.get(i);
			if (isRegistroProcurado(registroJaLancado, registroProcurado)) {
				return registroJaLancado;
			}
		}
		return null;
	}

	private boolean isRegistroProcurado(RegistroJaLancado registroJaLancado, RegistroEsperado registroProcurado) {
		String descricaoProcuradaLowerCase = registroProcurado.getDescricao().toLowerCase();
		if (registroProcurado.getFormaDePagamento().equals(registroJaLancado.getFormaDePagamento())
				&& registroJaLancado.getDescricao().toLowerCase().contains(descricaoProcuradaLowerCase)
				&& registroJaLancado.getOcorrencia() == registroProcurado.getOcorrencia()) {
			return true;
		}
		return false;
	}

}
