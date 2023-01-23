package br.com.trm.categorizadorautomaticodedespesas.sort;

import java.util.List;

import br.com.trm.categorizadorautomaticodedespesas.MovimentacaoDeRegistro;
import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;
import br.com.trm.categorizadorautomaticodedespesas.sort.OperacaoSobreRegistro.Operacao;

public class CoordenadorDeMovimentacao {
	private AtribuidorDeOrdinalDeOcorrencia atribuidorDeOrdinalDeOcorrencia;
	private LocalizadorDeRegistro localizadorDeRegistro;
	private CalculadoraMovimentacaoRegistro calculadoraMovimentacaoRegistro;
	private LeitorDeRegistrosJaLancados leitorDeRegistrosJaLancados;
	private ExecutorDeOperacao executorDeOperacao;

	public CoordenadorDeMovimentacao(AtribuidorDeOrdinalDeOcorrencia atribuidorDeOrdinalDeOcorrencia,
			LocalizadorDeRegistro localizadorDeRegistro,
			CalculadoraMovimentacaoRegistro calculadoraMovimentacaoRegistro,
			LeitorDeRegistrosJaLancados leitorDeRegistrosJaLancados, ExecutorDeOperacao executorDeOperacao) {
		this.atribuidorDeOrdinalDeOcorrencia = atribuidorDeOrdinalDeOcorrencia;
		this.localizadorDeRegistro = localizadorDeRegistro;
		this.calculadoraMovimentacaoRegistro = new CalculadoraMovimentacaoRegistro();
		this.leitorDeRegistrosJaLancados = leitorDeRegistrosJaLancados;
		this.executorDeOperacao = executorDeOperacao;
	}

	public void inserirOuMoverRegistros(List<RegistroEsperado> ordemEsperada) {
		for (RegistroEsperado registroEsperado : ordemEsperada) {
			OperacaoSobreRegistro operacao = identificaOperacao(registroEsperado, ordemEsperada);
			if (operacao != null) {
				executorDeOperacao.executa(registroEsperado, operacao);
			}
		}
	}

	private OperacaoSobreRegistro identificaOperacao(RegistroEsperado registroEsperado,
			List<RegistroEsperado> ordemEsperada) {
		List<RegistroJaLancado> registrosJaLancados = leitorDeRegistrosJaLancados.lerRegistrosJaLancados();
		atribuidorDeOrdinalDeOcorrencia.atribuiOrdinalDeCadaOcorrencia(ordemEsperada, registrosJaLancados);

		RegistroJaLancado registroJaLancadoEncontrado = localizadorDeRegistro.localizaRegistro(registroEsperado,
				registrosJaLancados);
		if (registroJaLancadoEncontrado == null) {
			return new OperacaoSobreRegistro(Operacao.INSERIR, null, registroEsperado.getPosicaoEsperada());
		} else {
			MovimentacaoDeRegistro movimentacao = calculadoraMovimentacaoRegistro.calculaMovimentacao(registroEsperado,
					registroJaLancadoEncontrado);
			if (movimentacao != null) {
				return new OperacaoSobreRegistro(Operacao.MOVER, movimentacao.getDaPosicao(),
						movimentacao.getParaPosicao());
			}
		}
		
		return null;
	}

}
