package br.com.trm.categorizadorautomaticodedespesas.sort;

public class OperacaoSobreRegistro {
	public enum Operacao {
		INSERIR, MOVER
	}

	private Operacao operacao;
	private Integer sourceRowNum;
	private Integer targetRowNum;

	public OperacaoSobreRegistro(Operacao operacao, Integer sourceRowNum, Integer targetRowNum) {
		this.operacao = operacao;
		this.sourceRowNum = sourceRowNum;
		this.targetRowNum = targetRowNum;
	}

	public Operacao getOperacao() {
		return operacao;
	}

	public Integer getSourceRowNum() {
		return sourceRowNum;
	}

	public Integer getTargetRowNum() {
		return targetRowNum;
	}

	@Override
	public String toString() {
		return "OperacaoSobreRegistro [operacao=" + operacao + ", sourceRowNum=" + sourceRowNum + ", targetRowNum="
				+ targetRowNum + "]";
	}

}
