package br.com.trm.categorizadorautomaticodedespesas.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.trm.categorizadorautomaticodedespesas.Despesa;
import br.com.trm.categorizadorautomaticodedespesas.DespesasJaLancadas;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DespesasJaLancadasTest {
    @Test
    public void deveEncontrarADespesa() {
        List<Despesa> despesasDoMesQueVem = new ArrayList<>();
        despesasDoMesQueVem.add(new Despesa("Conta da Energia - conta",
                "Energia Elétrica", new BigDecimal("87.15"),
                18, "CC Caixa", "NÃO"));
        despesasDoMesQueVem.add(new Despesa("Conta da caesb - conta",
                "Água", new BigDecimal("114.40"),
                18, "CC Caixa", "NÃO"));
        Map<YearMonth, List<Despesa>> despesasPorMesAno = new LinkedHashMap<>();
        despesasPorMesAno.put(YearMonth.now().plusMonths(1), despesasDoMesQueVem);
        DespesasJaLancadas despesasJaLancadas = new DespesasJaLancadas(despesasPorMesAno);
        Despesa despesaEncontrada = despesasJaLancadas.procuraDespesaContendoDescricao("CAESB");
        Assertions.assertTrue(despesaEncontrada != null);
    }
}
