package br.com.trm.categorizadorautomaticodedespesas.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.trm.categorizadorautomaticodedespesas.MovimentacaoDeRegistro;
import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;

class CalculadoraMovimentacaoRegistroTest {

	private CalculadoraMovimentacaoRegistro calculadoraMovimentacaoRegistro = new CalculadoraMovimentacaoRegistro();
	
	@Test
	void deveCalcularPosicoesCorretas() {
		RegistroEsperado registroProcurado = new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 101);
		RegistroJaLancado registroEncontrado = new RegistroJaLancado("Uber App", new BigDecimal("7.00"), "Fatura Nubank X", 1, 102);
		
		MovimentacaoDeRegistro movimentacao = calculadoraMovimentacaoRegistro.calculaMovimentacao(registroProcurado, registroEncontrado);
		
		assertEquals(Integer.valueOf(102), movimentacao.getDaPosicao());
		assertEquals(Integer.valueOf(101), movimentacao.getParaPosicao());
	}

}
