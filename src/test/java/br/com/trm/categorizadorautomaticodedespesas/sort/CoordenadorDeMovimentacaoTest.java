package br.com.trm.categorizadorautomaticodedespesas.sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;
import br.com.trm.categorizadorautomaticodedespesas.sort.OperacaoSobreRegistro.Operacao;

class CoordenadorDeMovimentacaoTest {

	private List<RegistroEsperado> ordemEsperada;
	private List<RegistroJaLancado> registrosJaLancados;
	private LeitorDeRegistrosJaLancados leitorDeRegistrosJaLancados;
	private CoordenadorDeMovimentacao coordenadorDeMovimentacao;
	private ExecutorDeOperacao executorDeOperacao;

	private static class ExecutorDeOperacaoParaTest implements ExecutorDeOperacao {

		private List<RegistroJaLancado> registrosJaLancados;

		public ExecutorDeOperacaoParaTest(List<RegistroJaLancado> registrosJaLancados) {
			this.registrosJaLancados = registrosJaLancados;
		}

		@Override
		public void executa(RegistroEsperado registroEsperado, OperacaoSobreRegistro operacao) {
			System.out.println("-----------------------------------");
			System.out.println("Antes: " + Arrays.toString(registrosJaLancados.toArray()));
			System.out.println("Executando " + operacao);
			
			if (operacao.getOperacao() == Operacao.INSERIR) {
				RegistroJaLancado registroParaLancar = new RegistroJaLancado(registroEsperado.getDescricao(), registroEsperado.getValor(), registroEsperado.getFormaDePagamento(), registroEsperado.getOcorrencia(), registroEsperado.getPosicaoEsperada());
				registrosJaLancados.add(operacao.getTargetRowNum(), registroParaLancar);
				
				System.out.println("Depois de inserir: " + Arrays.toString(registrosJaLancados.toArray()));				
			} else if (operacao.getOperacao() == Operacao.MOVER) {
				//Os registros irão trocar de posição
				registrosJaLancados.get(operacao.getSourceRowNum()).setPosicaoLancada(operacao.getTargetRowNum());
				registrosJaLancados.get(operacao.getTargetRowNum()).setPosicaoLancada(operacao.getSourceRowNum());
				Collections.swap(registrosJaLancados, operacao.getSourceRowNum(), operacao.getTargetRowNum());
				
				System.out.println("Depois de mover: " + Arrays.toString(registrosJaLancados.toArray()));
			} else {
				throw new UnsupportedOperationException("Operação não suportada: " + operacao.getOperacao());
			}
		}

	}

	@BeforeEach
	void init() {
		ordemEsperada = new ArrayList<>();
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 0));
		ordemEsperada.add(new RegistroEsperado("Supermercado", new BigDecimal("100.00"), "Fatura Nubank X", 1, 1));
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 2, 2));
		ordemEsperada.add(new RegistroEsperado("Farmácia", new BigDecimal("15.00"), "Fatura Nubank X", 1, 3));

		leitorDeRegistrosJaLancados = Mockito.mock(LeitorDeRegistrosJaLancados.class);

		
		registrosJaLancados = new ArrayList<>();
		registrosJaLancados.add(
				new RegistroJaLancado("Supermercado Xpto", new BigDecimal("100.00"), "Fatura Nubank X", null, 0));
		registrosJaLancados
				.add(new RegistroJaLancado("Uber App", new BigDecimal("7.00"), "Fatura Nubank X", null, 1));
		registrosJaLancados
				.add(new RegistroJaLancado("Outra despesa", new BigDecimal("7.00"), "Fatura Nubank X", null, 2));

		Mockito.when(leitorDeRegistrosJaLancados.lerRegistrosJaLancados()).thenReturn(registrosJaLancados);
		
		executorDeOperacao = new ExecutorDeOperacaoParaTest(registrosJaLancados);

		coordenadorDeMovimentacao = new CoordenadorDeMovimentacao(new AtribuidorDeOrdinalDeOcorrencia(),
				new LocalizadorDeRegistro(), new CalculadoraMovimentacaoRegistro(), leitorDeRegistrosJaLancados,
				executorDeOperacao);
	}

	@Test
	void testInserirOuMoverRegistros() {
		coordenadorDeMovimentacao.inserirOuMoverRegistros(ordemEsperada);
	}

}
