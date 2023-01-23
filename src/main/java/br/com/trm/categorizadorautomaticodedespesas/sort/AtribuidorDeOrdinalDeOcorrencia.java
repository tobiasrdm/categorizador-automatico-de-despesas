package br.com.trm.categorizadorautomaticodedespesas.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.trm.categorizadorautomaticodedespesas.AgrupadorDeOcorrencias;
import br.com.trm.categorizadorautomaticodedespesas.Registro;
import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;

public class AtribuidorDeOrdinalDeOcorrencia {

	public void atribuiOrdinalDeCadaOcorrencia(List<RegistroEsperado> registrosEsperados,
			List<? extends Registro> registrosParaAtribuirOrdinal) {
		Map<AgrupadorDeOcorrencias, Integer> mapaDeQtdOcorrencias = new HashMap<>();
		for (RegistroEsperado registroProcurado : registrosEsperados) {
			for (Registro registroParaAtribuirOrdinal : registrosParaAtribuirOrdinal) {
				if (isRegistroProcurado(registroParaAtribuirOrdinal, registroProcurado)) {
					if (registroParaAtribuirOrdinal.getOcorrencia() != null) {
						// Passa para o próximo porque este já foi contabilizado
						continue;
					}
					AgrupadorDeOcorrencias agrupadorDeOcorrencias = new AgrupadorDeOcorrencias(
							registroProcurado.getDescricao(), registroProcurado.getValor(),
							registroProcurado.getFormaDePagamento());

					Integer qtdOcorrencias = mapaDeQtdOcorrencias.get(agrupadorDeOcorrencias);
					if (qtdOcorrencias == null) {
						qtdOcorrencias = 0;
					}
					mapaDeQtdOcorrencias.put(agrupadorDeOcorrencias, ++qtdOcorrencias);

					registroParaAtribuirOrdinal.setOcorrencia(qtdOcorrencias);
				}
			}
		}
	}

	private boolean isRegistroProcurado(Registro registroSendoAnalisado, RegistroEsperado registroProcurado) {
		String descricaoProcuradaLowerCase = registroProcurado.getDescricao().toLowerCase();
		if (registroProcurado.getFormaDePagamento().equals(registroSendoAnalisado.getFormaDePagamento())
				&& registroSendoAnalisado.getDescricao().toLowerCase().contains(descricaoProcuradaLowerCase)) {
			return true;
		}
		return false;
	}

}
