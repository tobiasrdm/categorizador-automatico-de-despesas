package br.com.trm.categorizadorautomaticodedespesas.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;
import br.com.trm.categorizadorautomaticodedespesas.DespesasJaLancadas;
import br.com.trm.categorizadorautomaticodedespesas.LeitorDeDespesasAPatirDeLinhas;
import br.com.trm.categorizadorautomaticodedespesas.ReceitasEDespesas;
import br.com.trm.categorizadorautomaticodedespesas.excel.FormatadorDeDespesasComTab;
import br.com.trm.categorizadorautomaticodedespesas.excel.io.LeitorDeArquivosDoExcel;

import javax.swing.JComboBox;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

public class TelaNovasDespesas {

	private JFrame frame;
	private JTextArea txtrText;
	private JComboBox<String> comboBoxFormaDePagamento;
	private JFormattedTextField frmtdtxtfldDia;
	private JLabel lblValorqtdderegistros;
	private JTextArea txtCreditosReceitas;
	private DespesasJaLancadas despesasJaLancadas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNovasDespesas window = new TelaNovasDespesas();
					window.carregaDespesasJaLancadas();
					window.carregaFormasDePagamento();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaNovasDespesas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 774, 550);
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		txtrText = new JTextArea();
		sl_panel.putConstraint(SpringLayout.WEST, txtrText, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, txtrText, -163, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, txtrText, -10, SpringLayout.EAST, panel);
		txtrText.setColumns(50);
		txtrText.setRows(20);
		txtrText.setText("");
		panel.add(txtrText);

		JButton btnFormatar = new JButton("Formatar");
		btnFormatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReceitasEDespesas receitasEDespesas = new LeitorDeDespesasAPatirDeLinhas().lerDespesas(txtrText.getText());
				for (Despesa despesa : receitasEDespesas.getDespesas()) {
					despesa.setDiaDoMes(Integer.valueOf(frmtdtxtfldDia.getText()));
					despesa.setFormaDePagamento(comboBoxFormaDePagamento.getSelectedItem().toString().trim());
					Despesa despesaJaLancada = despesasJaLancadas
							.procuraDespesaContendoDescricao(despesa.getDescricao());
					if (despesaJaLancada != null) {
						despesa.setCategoria(despesaJaLancada.getCategoria());
					}
				}
				//Formatando as despesas na tela
				List<String> registrosDespesas = new FormatadorDeDespesasComTab().formataDespesas(receitasEDespesas.getDespesas());
				txtrText.setText("");
				for (String registro : registrosDespesas) {
					if (txtrText.getText().length() > 0) {
						txtrText.setText(txtrText.getText() + "\n");
					}
					txtrText.setText(txtrText.getText() + registro);
				}
				txtrText.requestFocus();
				txtrText.selectAll();
				lblValorqtdderegistros.setText(String.valueOf(registrosDespesas.size()));
				//Formatando as receitas/créditos na tela
				List<String> registrosReceitas = new FormatadorDeDespesasComTab().formataDespesas(receitasEDespesas.getReceitas());
				txtCreditosReceitas.setText("");
				for (String registro : registrosReceitas) {
					if (txtCreditosReceitas.getText().length() > 0) {
						txtCreditosReceitas.setText(txtCreditosReceitas.getText() + "\n");
					}
					txtCreditosReceitas.setText(txtCreditosReceitas.getText() + registro);
				}
			}
		});

		comboBoxFormaDePagamento = new JComboBox<>();
		sl_panel.putConstraint(SpringLayout.NORTH, btnFormatar, 0, SpringLayout.NORTH, comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.NORTH, txtrText, 6, SpringLayout.SOUTH, comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.WEST, comboBoxFormaDePagamento, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, comboBoxFormaDePagamento, -520, SpringLayout.EAST, panel);
		panel.add(comboBoxFormaDePagamento);
		panel.add(btnFormatar);

		JLabel lblFormaDePagamento = new JLabel("Forma de Pagamento");
		sl_panel.putConstraint(SpringLayout.WEST, lblFormaDePagamento, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, comboBoxFormaDePagamento, 6, SpringLayout.SOUTH,
				lblFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.NORTH, lblFormaDePagamento, 10, SpringLayout.NORTH, panel);
		panel.add(lblFormaDePagamento);

		JLabel lblDia = new JLabel("Dia");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDia, 0, SpringLayout.NORTH, lblFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.WEST, lblDia, 99, SpringLayout.EAST, lblFormaDePagamento);
		panel.add(lblDia);

		frmtdtxtfldDia = new JFormattedTextField();
		sl_panel.putConstraint(SpringLayout.WEST, frmtdtxtfldDia, 6, SpringLayout.EAST, comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.EAST, frmtdtxtfldDia, -6, SpringLayout.WEST, btnFormatar);
		frmtdtxtfldDia.setColumns(5);
		sl_panel.putConstraint(SpringLayout.NORTH, frmtdtxtfldDia, 1, SpringLayout.NORTH, comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.SOUTH, frmtdtxtfldDia, -494, SpringLayout.SOUTH, panel);
		frmtdtxtfldDia.setText("25");
		panel.add(frmtdtxtfldDia);

		JButton btnLimpar = new JButton("Limpar");
		sl_panel.putConstraint(SpringLayout.WEST, btnLimpar, 401, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnFormatar, -6, SpringLayout.WEST, btnLimpar);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrText.setText("");
				lblValorqtdderegistros.setText("");
				txtCreditosReceitas.setText("");
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnLimpar, 0, SpringLayout.NORTH, comboBoxFormaDePagamento);
		panel.add(btnLimpar);

		JLabel lblQtdDeRegistros = new JLabel("Qtd de registros: ");
		sl_panel.putConstraint(SpringLayout.NORTH, lblQtdDeRegistros, 0, SpringLayout.NORTH, comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.WEST, lblQtdDeRegistros, 51, SpringLayout.EAST, btnLimpar);
		panel.add(lblQtdDeRegistros);

		lblValorqtdderegistros = new JLabel("");
		sl_panel.putConstraint(SpringLayout.NORTH, lblValorqtdderegistros, 0, SpringLayout.NORTH,
				comboBoxFormaDePagamento);
		sl_panel.putConstraint(SpringLayout.WEST, lblValorqtdderegistros, 6, SpringLayout.EAST, lblQtdDeRegistros);
		panel.add(lblValorqtdderegistros);
		
		JLabel lblCreditosReceitas = new JLabel("Créditos/Receitas");
		sl_panel.putConstraint(SpringLayout.NORTH, lblCreditosReceitas, 6, SpringLayout.SOUTH, txtrText);
		sl_panel.putConstraint(SpringLayout.WEST, lblCreditosReceitas, 0, SpringLayout.WEST, txtrText);
		panel.add(lblCreditosReceitas);
		
		txtCreditosReceitas = new JTextArea();
		lblCreditosReceitas.setLabelFor(txtCreditosReceitas);
		sl_panel.putConstraint(SpringLayout.NORTH, txtCreditosReceitas, 6, SpringLayout.SOUTH, lblCreditosReceitas);
		sl_panel.putConstraint(SpringLayout.WEST, txtCreditosReceitas, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, txtCreditosReceitas, 133, SpringLayout.SOUTH, lblCreditosReceitas);
		sl_panel.putConstraint(SpringLayout.EAST, txtCreditosReceitas, 0, SpringLayout.EAST, txtrText);
		panel.add(txtCreditosReceitas);
	}

	private void carregaDespesasJaLancadas() throws InvalidFormatException, IOException {
		final String propertyDirArquivosExcel = "dirArquivosExcel";
		String strDiretorioDeArquivosDoExcel = System.getProperty(propertyDirArquivosExcel);
		if (strDiretorioDeArquivosDoExcel == null) {
			throw new RuntimeException("A propriedade " + propertyDirArquivosExcel
					+ " não foi definida. Informe usando -D" + propertyDirArquivosExcel + "=\"caminho\"");
		}
		Path diretorioDeArquivosDoExcel = Paths.get(strDiretorioDeArquivosDoExcel);
		Map<YearMonth, List<Despesa>> despesasPorMesAno = new LeitorDeArquivosDoExcel()
				.lerDespesasDosArquivosDoExcel(diretorioDeArquivosDoExcel);
		despesasJaLancadas = new DespesasJaLancadas(despesasPorMesAno);
	}

	private void carregaFormasDePagamento() {
		for (String formaDePagamento : despesasJaLancadas.getFormasDePagamento()) {
			comboBoxFormaDePagamento.addItem(formaDePagamento);
			if (formaDePagamento.startsWith("Fatura")) {
				comboBoxFormaDePagamento.setSelectedItem(formaDePagamento);
			}
		}

	}
}
