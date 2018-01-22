package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorDeDespesasAPatirDeLinhas {
	public List<Despesa> lerDespesas(String stringDespesas) {
		//Substitui todos os Enter por Tab, colocando todos os registros em uma única linha
		stringDespesas = stringDespesas.replace("\n", "\t");
		List<String> campos = Arrays.asList(stringDespesas.split("\t"));
		int umNovoRegistroACadaQuantosCampos = 3;
		List<String> valoresDosCampos = new ArrayList<>();
		List<List<String>> valoresDosRegistros = new ArrayList<>();
		for (int campo = 0; campo < campos.size(); campo++) {
			int registroDoCampoAnterior = ((campo - 1) / umNovoRegistroACadaQuantosCampos);
			int registroAtual = (campo / umNovoRegistroACadaQuantosCampos);
			int registroDoProximoCampo = ((campo + 1) / umNovoRegistroACadaQuantosCampos);
			if (registroAtual != registroDoCampoAnterior) {
				// Inicia novo registro
				valoresDosCampos = new ArrayList<>();
			}
			valoresDosCampos.add(campos.get(campo));
			// Verificando se é o última campo do registro
			if (registroDoProximoCampo != registroAtual) {
				valoresDosRegistros.add(valoresDosCampos);
			}
		}
		List<Despesa> despesas = new ArrayList<>();
		for (List<String> valoresDasColunas : valoresDosRegistros) {
			Despesa despesa = new Despesa();
			despesa.setDescricao(valoresDasColunas.get(1).trim());
			if (despesa.getDescricao().endsWith(" -")) {
				// Removendo o sufixo " -" da descrição
				despesa.setDescricao(despesa.getDescricao().substring(0, despesa.getDescricao().lastIndexOf(" -")));
			}
			// Remove os pontos agrupadores de milhar e depois substitui vírgula
			// decimal por ponto decimal
			despesa.setValor(new BigDecimal(valoresDasColunas.get(2).replace(".", "").replace(",", ".")));
			despesa.setRealizado("NÃO");
			if (despesa.getValor().compareTo(BigDecimal.ZERO) >= 0) {
				// Só coloca na lista se o valor da despesa for maior ou igual a
				// zero.
				// Se for menor que zero não é uma despesa, mas sim um crédito
				despesas.add(despesa);
			}
		}
		return despesas;
	}
}
