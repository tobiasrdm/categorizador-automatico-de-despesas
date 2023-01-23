package br.com.trm.categorizadorautomaticodedespesas.excel.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import br.com.trm.categorizadorautomaticodedespesas.RegistroEsperado;
import br.com.trm.categorizadorautomaticodedespesas.RegistroJaLancado;
import br.com.trm.categorizadorautomaticodedespesas.sort.ExecutorDeOperacao;
import br.com.trm.categorizadorautomaticodedespesas.sort.OperacaoSobreRegistro;
import br.com.trm.categorizadorautomaticodedespesas.sort.OperacaoSobreRegistro.Operacao;

public class ExecutorDeOperacaoExcel implements ExecutorDeOperacao {

	private XSSFSheet sheet;

	public ExecutorDeOperacaoExcel(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public void executa(RegistroEsperado registroEsperado, OperacaoSobreRegistro operacao) {

		System.out.println("-----------------------------------");
		System.out.println("Executando " + operacao);

		if (operacao.getOperacao() == Operacao.INSERIR) {
			// RegistroJaLancado registroParaLancar = new
			// RegistroJaLancado(registroEsperado.getDescricao(),
			// registroEsperado.getValor(), registroEsperado.getFormaDePagamento(),
			// registroEsperado.getOcorrencia(), registroEsperado.getPosicaoEsperada());
			// registrosJaLancados.add(operacao.getTargetRowNum(), registroParaLancar);
			
			//Implementação de acordo com https://stackoverflow.com/questions/5785724/how-to-insert-a-row-between-two-rows-in-an-existing-excel-with-hssf-apache-poi
			XSSFRow destinationRow = sheet.getRow(operacao.getTargetRowNum());
			if (destinationRow != null) {
			   //Insere uma linha em branco em TargetRowNum 
			   sheet.shiftRows(operacao.getTargetRowNum(), sheet.getLastRowNum(), 1);
			}
			if (sheet.getRow(operacao.getTargetRowNum()) != null) {
				throw new RuntimeException("Destination row is not empty");
			}
			int rownumModelo = 3;
			sheet.copyRows(rownumModelo, rownumModelo, operacao.getTargetRowNum(), new CellCopyPolicy.Builder().cellFormula(true).cellStyle(true).rowHeight(true).build());
			//destinationRow = sheet.createRow(operacao.getTargetRowNum());
			destinationRow = sheet.getRow(operacao.getTargetRowNum());
			preencheRow(destinationRow, registroEsperado);

			// System.out.println("Depois de inserir: " +
			// Arrays.toString(registrosJaLancados.toArray()));
		} else if (operacao.getOperacao() == Operacao.MOVER) {
			// Os registros irão trocar de posição
			// sheet.shiftRows(operacao.getSourceRowNum(), operacao.getTargetRowNum(), 1);
			// registrosJaLancados.get(operacao.getSourceRowNum()).setPosicaoLancada(operacao.getTargetRowNum());
			// registrosJaLancados.get(operacao.getTargetRowNum()).setPosicaoLancada(operacao.getSourceRowNum());
			// Collections.swap(registrosJaLancados, operacao.getSourceRowNum(),
			// operacao.getTargetRowNum());

			// System.out.println("Depois de mover: " +
			// Arrays.toString(registrosJaLancados.toArray()));
		} else {
			throw new UnsupportedOperationException("Operação não suportada: " + operacao.getOperacao());
		}

	}

	private void preencheRow(XSSFRow row, RegistroEsperado registroEsperado) {
		int colunaDescricao = 0;
		int colunaCategoria = 1;
		int colunaValor = 2;
		int colunaDiaDoMes = 3;
		int colunaFormaDePagamento = 4;
		int colunaRealizado = 5;
		int colunaControle = 6;

//		row.createCell(colunaDescricao, CellType.STRING).setCellValue(registroEsperado.getDescricao());
//		row.createCell(colunaCategoria, CellType.STRING).setCellValue("Categoria");// registroEsperado.getCategoria());
//		row.createCell(colunaValor, CellType.NUMERIC).setCellValue(registroEsperado.getValor().doubleValue());
//		row.createCell(colunaDiaDoMes, CellType.NUMERIC).setCellValue(25);// registroEsperado.getDiaDoMes());
//		row.createCell(colunaFormaDePagamento, CellType.STRING).setCellValue(registroEsperado.getFormaDePagamento());
//		row.createCell(colunaRealizado, CellType.STRING).setCellValue("NÃO");//registroEsperado.getRealizado());
		
		System.out.println("Descrição anterior: " + row.getCell(colunaDescricao).getStringCellValue());
		
		row.getCell(colunaDescricao).setCellValue(registroEsperado.getDescricao());
		row.getCell(colunaCategoria).setCellValue("Categoria");// registroEsperado.getCategoria());
		row.getCell(colunaValor).setCellValue(registroEsperado.getValor().doubleValue());
		row.getCell(colunaDiaDoMes).setCellValue(25);// registroEsperado.getDiaDoMes());
		row.getCell(colunaFormaDePagamento).setCellValue(registroEsperado.getFormaDePagamento());
		//row.getCell(colunaRealizado).setCellValue("NÃO");//registroEsperado.getRealizado());
		row.createCell(colunaControle, CellType.STRING).setCellValue("RegistroEsperado " + registroEsperado.getPosicaoEsperada());//registroEsperado.getRealizado());
	}

}
