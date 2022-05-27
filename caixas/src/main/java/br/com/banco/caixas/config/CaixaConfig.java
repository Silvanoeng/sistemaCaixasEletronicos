package br.com.banco.caixas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("banco")
public class CaixaConfig {
    private String tipoMoeda;
    private String notas;
    private String moedas;


    public CaixaConfig() { }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getMoedas() {
        return moedas;
    }

    public void setMoedas(String moedas) {
        this.moedas = moedas;
    }

    public String getTipoMoeda() {
        return tipoMoeda;
    }

    public void setTipoMoeda(String tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }
}
