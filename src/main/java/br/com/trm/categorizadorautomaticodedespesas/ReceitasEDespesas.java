package br.com.trm.categorizadorautomaticodedespesas;

import java.util.ArrayList;
import java.util.List;

public class ReceitasEDespesas {
	private List<Despesa> despesas = new ArrayList<>();
	private List<Despesa> receitas = new ArrayList<>();

	public List<Despesa> getDespesas() {
		return despesas;
	}

	public List<Despesa> getReceitas() {
		return receitas;
	}
}
