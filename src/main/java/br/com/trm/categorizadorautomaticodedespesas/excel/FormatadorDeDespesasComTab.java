package br.com.trm.categorizadorautomaticodedespesas.excel;

import java.util.ArrayList;
import java.util.List;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;

public class FormatadorDeDespesasComTab {

	public List<String> formataDespesas(List<Despesa> despesas) {
		List<String> registrosFormatados = new ArrayList<>();
		for (Despesa despesa : despesas) {
			registrosFormatados.add(formata(despesa));
		}
		return registrosFormatados;
	}

	private String formata(Despesa despesa) {
		return new StringBuilder().append(despesa.getDescricao()).append("\t").append(nullAsEmpty(despesa.getCategoria()))
				.append("\t").append(despesa.getValor().toString().replace(".", ",")).append("\t")
				.append(nullAsEmpty(despesa.getDiaDoMes())).append("\t").append(nullAsEmpty(despesa.getFormaDePagamento())).append("\t")
				.append(despesa.getRealizado()).toString();
	}

	private String nullAsEmpty(String valor) {
		if (valor != null) {
			return valor;
		} else {
			return "";
		}
	}
	
	private String nullAsEmpty(Integer valor) {
		if (valor != null) {
			return valor.toString();
		} else {
			return "";
		}
	}
}
