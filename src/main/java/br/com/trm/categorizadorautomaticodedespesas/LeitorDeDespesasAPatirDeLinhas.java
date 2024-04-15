package br.com.trm.categorizadorautomaticodedespesas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorDeDespesasAPatirDeLinhas {
	public List<Despesa> lerDespesas(String stringDespesas) {
		// Substitui todos os Enters por Tab, colocando todos os registros em uma única linha
		stringDespesas = stringDespesas.replace("\n\n", "\t");
		List<String> campos = Arrays.asList(stringDespesas.split("\t"));
		List<String> valoresDosCampos = new ArrayList<>();
		List<List<String>> valoresDosRegistros = new ArrayList<>();
		for (int campo = 0; campo < campos.size(); campo++) {
			String valorDoCampoAtual = campos.get(campo); 
			valoresDosCampos.add(valorDoCampoAtual);
			// Verificando se é o último campo do registro
			if (valorDoCampoAtual != null && valorDoCampoAtual.startsWith("R$")) {
				valoresDosRegistros.add(valoresDosCampos);
				// Inicia novo registro
				valoresDosCampos = new ArrayList<>();
			}
		}
		List<Despesa> despesas = new ArrayList<>();
		for (List<String> valoresDasColunas : valoresDosRegistros) {
			int indexColunaValor = valoresDasColunas.size() -1;//Última coluna
			int indexColunaDescricao = indexColunaValor -1;//Penúltima coluna
			Despesa despesa = new Despesa();
			despesa.setDescricao(valoresDasColunas.get(indexColunaDescricao).trim());
			if (despesa.getDescricao().endsWith(" -")) {
				// Removendo o sufixo " -" da descrição
				despesa.setDescricao(despesa.getDescricao().substring(0, despesa.getDescricao().lastIndexOf(" -")));
			}
			// Remove os pontos agrupadores de milhar e depois substitui vírgula
			// decimal por ponto decimal
			despesa.setValor(new BigDecimal(valoresDasColunas.get(indexColunaValor).replace("R$ ", "").replace(".", "").replace(",", ".")));
			despesa.setRealizado("NÃO");
			if (adicionarDespesa(despesa)) {
				despesas.add(despesa);
			}
		}
		return despesas;
	}
	
	private boolean adicionarDespesa(Despesa despesa) {
		if ("Pagamento recebido".equals(despesa.getDescricao())) {
			// Se a descrição é "Pagamento recebido", então não é uma despesa, mas sim um
			// crédito.
			return false;
		}
		// Só coloca na lista se o valor da despesa for maior ou igual a zero.
		// Se for menor que zero então não é uma despesa, mas sim um crédito
		return (despesa.getValor().compareTo(BigDecimal.ZERO) >= 0);
	}
}
