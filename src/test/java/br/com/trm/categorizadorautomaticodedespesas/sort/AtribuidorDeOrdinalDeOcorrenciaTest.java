package br.com.trm.categorizadorautomaticodedespesas.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.trm.categorizadorautomaticodedespesas.Registro;
import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;

class AtribuidorDeOrdinalDeOcorrenciaTest {

	private List<RegistroEsperado> ordemEsperada;
	private List<Registro> registrosParaAtribuirOrdinal;
	private AtribuidorDeOrdinalDeOcorrencia atribuidorDeOrdinalDeOcorrencia = new AtribuidorDeOrdinalDeOcorrencia();


	@BeforeEach
	void init() {
		ordemEsperada = new ArrayList<>();
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 101));
		ordemEsperada.add(new RegistroEsperado("Supermercado", new BigDecimal("100.00"), "Fatura Nubank X", 1, 102));
		ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 2, 103));
		ordemEsperada.add(new RegistroEsperado("Farmácia", new BigDecimal("15.00"), "Fatura Nubank X", 1, 104));
		
		registrosParaAtribuirOrdinal = new ArrayList<>();
		registrosParaAtribuirOrdinal.add(
				new RegistroJaLancado("Supermercado Xpto", new BigDecimal("100.00"), "Fatura Nubank X", null, 101));
		registrosParaAtribuirOrdinal.add(new RegistroJaLancado("Uber App", new BigDecimal("7.00"), "Fatura Nubank X", null, 102));
		registrosParaAtribuirOrdinal.add(new RegistroJaLancado("Farmácia Xyz", new BigDecimal("7.00"), "Fatura Nubank X", null, 102));
		registrosParaAtribuirOrdinal.add(new RegistroJaLancado("Uber ZZ", new BigDecimal("7.00"), "Fatura Nubank X", null, 102));
		registrosParaAtribuirOrdinal.add(new RegistroJaLancado("Outra despesa", new BigDecimal("7.00"), "Fatura Nubank X", null, 102));
	}

	@Test
	void test() {
		atribuidorDeOrdinalDeOcorrencia.atribuiOrdinalDeCadaOcorrencia(ordemEsperada, registrosParaAtribuirOrdinal);
		
		assertEquals(Integer.valueOf(1), registrosParaAtribuirOrdinal.get(0).getOcorrencia());
		assertEquals(Integer.valueOf(1), registrosParaAtribuirOrdinal.get(1).getOcorrencia());
		assertEquals(Integer.valueOf(1), registrosParaAtribuirOrdinal.get(2).getOcorrencia());
		assertEquals(Integer.valueOf(2), registrosParaAtribuirOrdinal.get(3).getOcorrencia());
		assertNull(registrosParaAtribuirOrdinal.get(4).getOcorrencia());
	}

}
