package br.com.trm.categorizadorautomaticodedespesas.excel;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorDeAbreviaturaDoMesAno {
	public String format(YearMonth mesAno) {
		String abreviaturaDoMesAno = mesAno.format(DateTimeFormatter.ofPattern("MMM-yyyy", new Locale("pt", "BR")));
		abreviaturaDoMesAno = abreviaturaDoMesAno.substring(0, 1).toUpperCase() + abreviaturaDoMesAno.substring(1);
		abreviaturaDoMesAno = abreviaturaDoMesAno.replace(".", "");
		return abreviaturaDoMesAno;
	}

}
