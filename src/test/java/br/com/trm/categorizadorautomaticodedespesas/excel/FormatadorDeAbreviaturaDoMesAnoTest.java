package br.com.trm.categorizadorautomaticodedespesas.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Month;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

class FormatadorDeAbreviaturaDoMesAnoTest {

	@Test
	void format() {
		FormatadorDeAbreviaturaDoMesAno formatador = new FormatadorDeAbreviaturaDoMesAno();
		assertEquals("Jan-2022", formatador.format(YearMonth.of(2022, Month.JANUARY)));
		assertEquals("Fev-2022", formatador.format(YearMonth.of(2022, Month.FEBRUARY)));
		assertEquals("Mar-2022", formatador.format(YearMonth.of(2022, Month.MARCH)));
		assertEquals("Abr-2022", formatador.format(YearMonth.of(2022, Month.APRIL)));
		assertEquals("Mai-2022", formatador.format(YearMonth.of(2022, Month.MAY)));
		assertEquals("Jun-2022", formatador.format(YearMonth.of(2022, Month.JUNE)));
		assertEquals("Jul-2022", formatador.format(YearMonth.of(2022, Month.JULY)));
		assertEquals("Ago-2022", formatador.format(YearMonth.of(2022, Month.AUGUST)));
		assertEquals("Set-2022", formatador.format(YearMonth.of(2022, Month.SEPTEMBER)));
		assertEquals("Out-2022", formatador.format(YearMonth.of(2022, Month.OCTOBER)));
		assertEquals("Nov-2022", formatador.format(YearMonth.of(2022, Month.NOVEMBER)));
		assertEquals("Dez-2022", formatador.format(YearMonth.of(2022, Month.DECEMBER)));
	}

}
