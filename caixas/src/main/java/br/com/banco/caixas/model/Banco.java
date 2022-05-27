package br.com.banco.caixas.model;

import br.com.banco.caixas.config.CaixaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class Banco {
    private final AtomicLong numCaixa = new AtomicLong();
    private final Map<Long, CaixaRegistratora> caixasBanco = new HashMap<>();
    @Autowired
    private CaixaConfig config;

    public Banco() {
    }

    public Long novoCaixaBanco( ) {
        List<Double> notasList = new ArrayList<>();
        List<Double> moedasList = new ArrayList<>();
        String notas = config.getNotas();
        String[] arrayNotas = notas.split(",");
        for (String nota : arrayNotas){
            notasList.add(Double.parseDouble(nota));
        }
        String moedas = config.getMoedas();
        String[] arrayMoedas = moedas.split(",");
        for (String moeda : arrayMoedas){
            moedasList.add(Double.parseDouble(moeda));
        }
        CaixaRegistratora novoCaixa = new CaixaRegistratora(config.getTipoMoeda(), notasList, moedasList);
        Long nCaixa = numCaixa.incrementAndGet();
        this.caixasBanco.put(nCaixa,novoCaixa);
        return nCaixa;
    }

    public CaixaRegistratora buscaCaixa(Long numCaixa){
        CaixaRegistratora caixa = caixasBanco.get(numCaixa);
        return caixa;
    }

    public Double saldoDoCaixa(Long numCaixa){
        return buscaCaixa(numCaixa).totalDoCaixa();
    }

    public void pagarBoleto(Long numCaixa, Double valor){
        buscaCaixa(numCaixa).pagarBoleto(valor);
    }

    public void sacar(Long numCaixa, Double valor){
        buscaCaixa(numCaixa).retirar(valor);
    }

    public void depositar(Long numCaixa, Double valor){
        buscaCaixa(numCaixa).depositar(valor);
    }

    public String tipoMoeda(Long numCaixa){
        return buscaCaixa(numCaixa).getTipoDeMoeda();
    }

    public String relatorioCaixa(Long numCaixa){
        return buscaCaixa(numCaixa).relatorioDoCaixa();
    }

    public String relatorioBanco(){
        String caixas = "Número dos caixas do banco | ";
        for (Long i = 1L; i <= numCaixa.get(); i++ ){
            caixas+= i + " | ";
        }
        return caixas;
    }
}
