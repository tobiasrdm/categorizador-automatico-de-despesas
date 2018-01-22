package br.com.trm.categorizadorautomaticodedespesas.excel.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;

public class LeitorDeArquivosDoExcel {

	public Map<YearMonth, List<Despesa>> lerDespesasDosArquivosDoExcel(Path diretorioDeArquivosDoExcel)
			throws InvalidFormatException, IOException {
		if (Files.exists(diretorioDeArquivosDoExcel)) {
			List<File> arquivosDoExcel = obtemArquivosDeInteresse(diretorioDeArquivosDoExcel);
			ExtratorDeDespesasDoArquivoDoExcel extrator = new ExtratorDeDespesasDoArquivoDoExcel();
			Map<YearMonth, List<Despesa>> mapaDeDespesasPorMesAno = extrator.extraiDespesas(arquivosDoExcel);
			return mapaDeDespesasPorMesAno;
		}
		return null;
	}

	private List<File> obtemArquivosDeInteresse(Path diretorioDeArquivosDoExcel) {
		int anoAtual = LocalDate.now().getYear();
		int[] anosDeInteresse = { anoAtual - 1, anoAtual, anoAtual + 1 };
		File[] arquivosDoDiretorio = diretorioDeArquivosDoExcel.toFile().listFiles();
		List<File> arquivosDeInteresse = new ArrayList<>();
		for (File arquivo : arquivosDoDiretorio) {
			if (arquivo.isFile() && arquivo.getName().endsWith(".xlsx") && !arquivo.getName().startsWith("~$")) {
				for (int ano : anosDeInteresse) {
					if (arquivo.getName().contains(String.valueOf(ano))) {
						arquivosDeInteresse.add(arquivo);
						break;
					}
				}
			}
		}
		return arquivosDeInteresse;
	}

}
