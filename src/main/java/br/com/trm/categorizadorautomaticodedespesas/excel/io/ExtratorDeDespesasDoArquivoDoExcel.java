package br.com.trm.categorizadorautomaticodedespesas.excel.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;

public class ExtratorDeDespesasDoArquivoDoExcel {
	public Map<YearMonth, List<Despesa>> extraiDespesas(List<File> arquivosDoExcel)
			throws InvalidFormatException, IOException {
		List<YearMonth> mesesEAnos = getMesesQueSeraoAnalisados();
		Map<YearMonth, List<Despesa>> mapaDeDespesasPorMesAno = new LinkedHashMap<>();
		for (File arquivoDoExcel : arquivosDoExcel) {
			try (XSSFWorkbook workbook = new XSSFWorkbook(getInputStream(arquivoDoExcel))) {
				for (YearMonth mesAno : mesesEAnos) {
					List<Despesa> despesasDoMesAno = extraiDespesasDoMesAno(workbook, mesAno);
					if (despesasDoMesAno != null) {
						mapaDeDespesasPorMesAno.put(mesAno, despesasDoMesAno);
					}
				}
			}
		}
		return mapaDeDespesasPorMesAno;
	}

	/**
	 * Foi preciso ler todos os bytes do arquivo do Excel e passar um
	 * ByteArrayInputStream porque estava ocorrendo IOException ao fechar o
	 * workbook quando o arquivo já estava aberto pelo Microsoft Excel.
	 * 
	 * @param arquivoDoExcel o arquivo do Excel
	 * @return
	 * @throws IOException 
	 */
	private InputStream getInputStream(File arquivoDoExcel) throws IOException {
		byte[] bytesDoArquivoDoExcel = Files.readAllBytes(arquivoDoExcel.toPath());
		return new ByteArrayInputStream(bytesDoArquivoDoExcel);
	}

	private List<Despesa> extraiDespesasDoMesAno(XSSFWorkbook workbook, YearMonth mesAno) {
		String abreviaturaDoMesAno = mesAno.format(DateTimeFormatter.ofPattern("MMM-yyyy", new Locale("pt", "BR")));
		abreviaturaDoMesAno = new StringBuilder(abreviaturaDoMesAno.substring(0, 1).toUpperCase())
				.append(abreviaturaDoMesAno.substring(1)).toString();
		XSSFSheet sheet = workbook.getSheet(abreviaturaDoMesAno);
		if (sheet != null) {
			ExtratorDeDespesasDaPlanilha extratorDeDespesas = new ExtratorDeDespesasDaPlanilha();
			List<Despesa> despesasJaLancadas = extratorDeDespesas.extraiDespesas(sheet);
			return despesasJaLancadas;
		}
		return null;
	}

	private List<YearMonth> getMesesQueSeraoAnalisados() {
		YearMonth janeiroDoAnoPassado = YearMonth.of(Year.now().minusYears(1).getValue(), Month.JANUARY);
		YearMonth dezembroDoAnoQueVem = YearMonth.of(Year.now().plusYears(1).getValue(), Month.DECEMBER);
		YearMonth mesAnoSendoAnalisado = janeiroDoAnoPassado.minusMonths(1);
		List<YearMonth> mesesEAnos = new ArrayList<>();
		while (!mesAnoSendoAnalisado.equals(dezembroDoAnoQueVem)) {
			mesAnoSendoAnalisado = mesAnoSendoAnalisado.plusMonths(1);
			mesesEAnos.add(mesAnoSendoAnalisado);
		}
		return mesesEAnos;
	}

}
