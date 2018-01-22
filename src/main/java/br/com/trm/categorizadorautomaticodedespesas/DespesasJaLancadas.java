package br.com.trm.categorizadorautomaticodedespesas;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DespesasJaLancadas {
	private Map<YearMonth, List<Despesa>> despesasPorMesAno;
	private List<YearMonth> mesesParaPesquisar = null;

	public DespesasJaLancadas() {
		this.despesasPorMesAno = new LinkedHashMap<>();
	}

	public DespesasJaLancadas(Map<YearMonth, List<Despesa>> despesasPorMesAno) {
		this.despesasPorMesAno = despesasPorMesAno;
	}

	public Despesa procuraDespesaContendoDescricao(String descricaoProcurada) {
		String descricaoProcuradaLowerCase = descricaoProcurada.toLowerCase();
		for (YearMonth mesAno : getMesesParaPesquisar()) {
			List<Despesa> despesasDoMesAno = despesasPorMesAno.get(mesAno);
			for (Despesa despesa : despesasDoMesAno) {
				if (despesa.getDescricao().toLowerCase().contains(descricaoProcuradaLowerCase)) {
					return despesa;
				}
			}
		}
		return null;
	}

	public List<String> getFormasDePagamento() {
		// Usando Set para remover duplicatas
		Set<String> formasDePagamento = new HashSet<>();
		formasDePagamento.addAll(getFormasDePagamento(YearMonth.now().minusMonths(1)));
		formasDePagamento.addAll(getFormasDePagamento(YearMonth.now()));
		formasDePagamento.addAll(getFormasDePagamento(YearMonth.now().plusMonths(1)));
		List<String> formasDePagamentoClassificadas = new ArrayList<>(formasDePagamento);
		Collections.sort(formasDePagamentoClassificadas);
		return formasDePagamentoClassificadas;
	}

	private Set<String> getFormasDePagamento(YearMonth mesAno) {
		Set<String> formasDePagamento = new HashSet<>();
		if (despesasPorMesAno.get(mesAno) != null) {
			for (Despesa despesa : despesasPorMesAno.get(mesAno)) {
				formasDePagamento.add(despesa.getFormaDePagamento());
			}
		}
		return formasDePagamento;
	}

	private List<YearMonth> getMesesParaPesquisar() {
		if (mesesParaPesquisar == null) {
			mesesParaPesquisar = new ArrayList<>();
			YearMonth mesQueVem = YearMonth.now().plusMonths(1);
			for (YearMonth mesAno : despesasPorMesAno.keySet()) {
				// Adiciona todos os meses até chegar no mês que vem
				mesesParaPesquisar.add(mesAno);
				if (mesAno.equals(mesQueVem)) {
					break;
				}
			}
			// Ordena os meses e anos em ordem decrescente para pesquisar
			// primeiro no mês que vem, depois no mês atual, depois no mês
			// passado, e assim por diante
			Collections.sort(mesesParaPesquisar, Collections.reverseOrder());
		}
		return mesesParaPesquisar;
	}

}
