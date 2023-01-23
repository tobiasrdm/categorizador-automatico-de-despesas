package br.com.trm.categorizadorautomaticodedespesas.excel.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.sort.AtribuidorDeOrdinalDeOcorrencia;
import br.com.trm.categorizadorautomaticodedespesas.sort.CalculadoraMovimentacaoRegistro;
import br.com.trm.categorizadorautomaticodedespesas.sort.CoordenadorDeMovimentacao;
import br.com.trm.categorizadorautomaticodedespesas.sort.LeitorDeRegistrosJaLancados;
import br.com.trm.categorizadorautomaticodedespesas.sort.LocalizadorDeRegistro;

public class EditorDeArquivoDoExcel {

	
	public static void main(String[] args) {
		try {
			new EditorDeArquivoDoExcel().editarArquivoExcel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void editarArquivoExcel() throws IOException {
		String arquivoDoExcelParaLer = "D:\\ProjetosJava\\categorizador-automatico-de-despesas\\Orçamento doméstico - Modelo - 2018.xlsx";
		String arquivoDoExcelParaEditar = "D:/temp/Orçamento doméstico - Modelo - 2018 - para editar.xlsx";
	    Files.copy(Paths.get(arquivoDoExcelParaLer), Paths.get(arquivoDoExcelParaEditar), StandardCopyOption.REPLACE_EXISTING);
		try (XSSFWorkbook workbook = new XSSFWorkbook(arquivoDoExcelParaEditar)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//sheet.setForceFormulaRecalculation(true);
			
			List<RegistroEsperado> ordemEsperada = new ArrayList<RegistroEsperado>();
			ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 1, 5));
			ordemEsperada.add(new RegistroEsperado("Supermercado", new BigDecimal("100.00"), "Fatura Nubank X", 1, 6));
			ordemEsperada.add(new RegistroEsperado("Uber", new BigDecimal("7.00"), "Fatura Nubank X", 2, 7));
			ordemEsperada.add(new RegistroEsperado("Farmácia", new BigDecimal("15.00"), "Fatura Nubank X", 1, 8));
			
			ExecutorDeOperacaoExcel executorDeOperacaoExcel = new ExecutorDeOperacaoExcel(sheet);
			
			CoordenadorDeMovimentacao coordenadorDeMovimentacao = new CoordenadorDeMovimentacao(new AtribuidorDeOrdinalDeOcorrencia(), 
					new LocalizadorDeRegistro(), new CalculadoraMovimentacaoRegistro(), new LeitorDeRegistrosJaLancados(), executorDeOperacaoExcel);
			
			coordenadorDeMovimentacao.inserirOuMoverRegistros(ordemEsperada);
			
			workbook.write(new FileOutputStream(new File(arquivoDoExcelParaEditar + "-2.xlsx")));			
	    } catch (IOException e) {
			e.printStackTrace();
		}
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
}
