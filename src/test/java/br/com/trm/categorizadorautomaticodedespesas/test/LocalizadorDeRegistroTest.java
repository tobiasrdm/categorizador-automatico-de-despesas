package br.com.trm.categorizadorautomaticodedespesas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;
import br.com.trm.categorizadorautomaticodedespesas.sort.LocalizadorDeRegistro;

class LocalizadorDeRegistroTest {

	private List<RegistroEsperado> ordemEsperada;
	private LocalizadorDeRegistro localizadorDeRegistro = new LocalizadorDeRegistro();

	@BeforeEach
	void init() {
		ordemEsperada = new ArrayList<>();
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 101));
		ordemEsperada.add(new RegistroEsperado("Supermercado", new BigDecimal("100.00"), "Fatura Nubank X", 1, 102));
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 2, 103));
		ordemEsperada.add(new RegistroEsperado("Farmácia", new BigDecimal("15.00"), "Fatura Nubank X", 1, 104));
	}

	@Test
	void test() {
		RegistroEsperado registroProcurado = new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 101);
		RegistroJaLancado registroEncontrado = localizadorDeRegistro.localizaRegistro(registroProcurado, obtemRegistrosJaLancados());
		
		System.out.println("registroProcurado: " + registroProcurado);
		System.out.println("registroEncontrado: " + registroEncontrado);
		
		assertNotNull(registroEncontrado);
		assertEquals("Uber App", registroEncontrado.getDescricao());
		assertEquals(new BigDecimal("7.00"), registroEncontrado.getValor());
		assertEquals("Fatura Nubank X", registroEncontrado.getFormaDePagamento());
		assertEquals(Integer.valueOf(1), registroEncontrado.getOcorrencia());
		assertEquals(Integer.valueOf(102), registroEncontrado.getPosicaoLancada());
	}

	private List<RegistroJaLancado> obtemRegistrosJaLancados() {
		List<RegistroJaLancado> registrosJaLancados = new ArrayList<>();
		registrosJaLancados.add(new RegistroJaLancado("Supermercado Xpto", new BigDecimal("100.00"), "Fatura Nubank X", 1, 101));
		registrosJaLancados.add(new RegistroJaLancado("Uber App", new BigDecimal("7.00"), "Fatura Nubank X", 1, 102));
		return registrosJaLancados;
	}

}
