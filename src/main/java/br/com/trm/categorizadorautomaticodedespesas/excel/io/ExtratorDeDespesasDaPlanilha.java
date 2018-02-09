package br.com.trm.categorizadorautomaticodedespesas.excel.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;

public class ExtratorDeDespesasDaPlanilha {

	public List<Despesa> extraiDespesas(XSSFSheet sheet) {
		int primeiraLinhaDeLanchamentos = 2;
		List<Despesa> despesas = new ArrayList<>();
		for (int rowNum = primeiraLinhaDeLanchamentos; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			if (row.getCell(0, MissingCellPolicy.RETURN_BLANK_AS_NULL) == null) {
				// Se a célula da coluna A está vazia é porque já chegou no
				// final dos registros de despesas
				break;
			} else {
				despesas.add(extraiDespesa(row));
			}
		}
		return despesas;
	}

	public Despesa extraiDespesa(XSSFRow row) {
		int colunaDescricao = 0;
		int colunaCategoria = 1;
		int colunaValor = 2;
		int colunaDiaDoMes = 3;
		int colunaFormaDePagamento = 4;
		Despesa despesa = new Despesa();
		despesa.setDescricao(getStringCellValue(row.getCell(colunaDescricao, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
		despesa.setCategoria(getStringCellValue(row.getCell(colunaCategoria, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
		despesa.setValor(BigDecimal
				.valueOf(row.getCell(colunaValor, MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue()));
		despesa.setDiaDoMes(
				(int) row.getCell(colunaDiaDoMes, MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue());
		despesa.setFormaDePagamento(
				getStringCellValue(row.getCell(colunaFormaDePagamento, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
		return despesa;
	}
	
	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		return cell.getStringCellValue();		
	}

}
