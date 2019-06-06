package factory;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import singleton.Biblioteca;

public class CartaoCredito implements FormaPagamento {

    Scanner input = new Scanner(System.in);
    boolean verificar = false;

    public String solicitarCartao() {

        System.out.print("\nDigite o Número do Cartão de Crédito:  ");
        int numCartao = input.nextInt();
        System.out.print("\nDigite o Nome do Titular do Cartão de Crédito:  ");
        String titularCartao = input.next();
        System.out.print("\nDigite o Ano de Validade do Cartão de Crédito:  ");
        String validadeCartao = input.next();
        System.out.print("\nDigite o Código de Segurança do Cartão de Crédito:  ");
        int codigoCartao = input.nextInt();

        if (numCartao == 0 || titularCartao.isEmpty() || validadeCartao.isEmpty() || codigoCartao == 0) {
            verificar = false;
            return "\nDados Incorretos";
        } else {
            return comunicarBanco();
        }

    }

    public String comunicarBanco() {

        try {
            System.out.println("\nComunicando com o Banco........");
            Thread.sleep(4000);

        } catch (InterruptedException e) {
            verificar = false;
            return "\nFalha na Comunicação com o Banco"+e;

        }
        verificar = true;
        return "\nSucesso na Comunicação com o Banco";
    }

    @Override
    public String sendMessage(String mensagem) {

        if (verificar) {
            mensagem = "\nPagamento Realizado com Sucesso";

            return mensagem;
        } else {
            mensagem = "\nNão foi possível realizar o Pagamento";
            return mensagem;
        }

    }

    @Override
    public void processos(Double valorTotal, Double valorPago, String fPagamento) {

        try {
            System.out.print(solicitarCartao());
            Biblioteca.getInstance().ExportXML(fPagamento);
        } catch (IOException ex) {
            Logger.getLogger(CartaoCredito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
