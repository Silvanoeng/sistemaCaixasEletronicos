package br.com.banco.caixas.controller;

import br.com.banco.caixas.model.Banco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BancoController {
    @Autowired
    private Banco banco;

    @GetMapping("/banco")
    public String relatorioDoBanco(){
        return banco.relatorioBanco();
    }

    @GetMapping("/caixa")
    public String relatorioDoCaixa(@RequestParam(value = "caixa", defaultValue = "") Long nCaixa){
        return banco.relatorioCaixa(nCaixa);
    }

    @GetMapping("/total")
    public String totalDoCaixa(@RequestParam(value = "caixa", defaultValue = "") Long nCaixa){
        return ("O caixa n° " + nCaixa + " tem um total de " + banco.tipoMoeda(nCaixa) + " " + banco.saldoDoCaixa(nCaixa) + ".");
    }

    @RequestMapping("/new")
    public String newCaixa(){
        return ("O caixa de n° " + banco.novoCaixaBanco() + ", já esta operando.");
    }

    @RequestMapping("/transferir")
    public String transferirCaixa(@RequestParam(value = "caixa", defaultValue = "") Long nCaixa,
                                  @RequestParam(value = "valor", defaultValue = "") Double valor){
        banco.depositar(nCaixa, valor);
        return ("Sua transferência de " + banco.buscaCaixa(nCaixa).getTipoDeMoeda() + " " + valor + " foi realizada com sucesso.");
    }

    @RequestMapping("/pagar")
    public String pagar(@RequestParam(value = "caixa", defaultValue = "") Long nCaixa,
                                  @RequestParam(value = "valor", defaultValue = "") Double valor){
        banco.pagarBoleto(nCaixa, valor);
        return ("Seu pagamento no valor " + banco.buscaCaixa(nCaixa).getTipoDeMoeda() + " " + valor + " foi realizada com sucesso.");
    }

    @RequestMapping("/saque")
    public String saque(@RequestParam(value = "caixa", defaultValue = "") Long nCaixa,
                        @RequestParam(value = "valor", defaultValue = "") Double valor){
        banco.sacar(nCaixa, valor);
        return ("Seu saque no valor " + banco.buscaCaixa(nCaixa).getTipoDeMoeda() + " " + valor + " foi realizada com sucesso.");
    }


}
