package br.com.banco.caixas.model;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaixaRegistratora {
    private String tipoDeMoeda;
    private List<Double> notas;
    private Map<Double, Integer> qtdNotas = new HashMap<>();
    private List<Double> moedas;
    private Map<Double, Integer> qtdMoedas = new HashMap<>();
    private List<Double> faturas = new ArrayList<>();

    public CaixaRegistratora() {
    }

    public CaixaRegistratora(String tipoDeMoeda, List<Double> notas, List<Double> moedas) {
        this.tipoDeMoeda = tipoDeMoeda;
        this.notas = notas;
        for (Double valor : notas){
            qtdNotas.put(valor,0);
        }
        this.moedas = moedas;
        for (Double valor : moedas){
            qtdMoedas.put(valor,0);
        }
    }


    public void depositar(Double valorPago) {
        for (Double valor : this.notas){
            if (valorPago>=valor){
                int qtd = (int) (valorPago/valor);
                valorPago=valorPago-(qtd*valor);
                this.qtdNotas.put(valor, this.qtdNotas.get(valor)+qtd);
            }
        }
        for (Double valor : this.moedas){
            if (valorPago>=valor){
                int qtd = (int) (valorPago/valor);
                valorPago=valorPago-(qtd*valor);
                valorPago+=0.00002;
                this.qtdMoedas.put(valor, this.qtdMoedas.get(valor)+qtd);
            }
        }
    }

    public void retirar(Double valorSaque) {
        for (Double valor : this.notas){
            if (valorSaque>=valor){
                int qtd = (int) (valorSaque/valor);
                valorSaque=valorSaque-(qtd*valor);
                this.qtdNotas.put(valor, this.qtdNotas.get(valor)-qtd);
            }
        }
        for (Double valor : this.moedas){
            if (valorSaque>=valor){
                int qtd = (int) (valorSaque/valor);
                valorSaque=valorSaque-(qtd*valor);
                this.qtdMoedas.put(valor, this.qtdMoedas.get(valor)-qtd);
            }
        }
    }

    public Double totalDoCaixa(){
        Double total = 0.0;
        for (Double valor : this.notas){
            total += this.qtdNotas.get(valor) * valor;
        }
        for (Double valor : this.moedas){
            total += this.qtdMoedas.get(valor) * valor;
        }
        return total;
    }

    public void pagarBoleto(Double valorBoleto){
        this.faturas.add(valorBoleto);
        depositar(valorBoleto);
    }

    public String getTipoDeMoeda() {
        return tipoDeMoeda;
    }

    public String relatorioDoCaixa(){
        String relatorioNotas = "";
        String relatorioMoedas = "";
        for (Double valor : this.notas){

            relatorioNotas += " | " + this.tipoDeMoeda + " " + valor + " qtd= " + this.qtdNotas.get(valor);
        }
        for (Double valor : this.moedas){
            relatorioMoedas += " | " + this.tipoDeMoeda + " " + valor + " qtd= " + this.qtdMoedas.get(valor);
        }
        return ("* Notas" + relatorioNotas + " | * Moedas" + relatorioMoedas + " |");
    }
}
