package factory;

import java.io.IOException;
import singleton.Biblioteca;

public class Dinheiro implements FormaPagamento {

    @Override
    public String sendMessage(String mensagem) {
        return "";
    }

    @Override
    public void processos(Double valorTotal, Double valorPago, String fPagamento) {
        try {
            if (valorPago <= 0.0) {
                System.out.println("\nValor Inválido!");

            } else if (valorPago >= valorTotal) {

                if (valorTotal == valorPago) {
                    System.out.println("\nPagamento Efetuado com Sucesso.");
                    Biblioteca.getInstance().ExportXML(fPagamento);

                } else if (valorTotal <= valorPago) {
                    Double somaTotal = valorPago - valorTotal;
                    System.out.println("Troco: R$" + String.format("%.2f", somaTotal) + "\nPagamento Efetuado com Sucesso.");
                    Biblioteca.getInstance().ExportXML(fPagamento);

                }

            } else if (valorPago < valorTotal) {
                System.out.println("\nO Valor Pago está Incorreto. O Valor valorTotal é R$:" + valorTotal
                        + " e o Valor Pago é R$:" + valorPago);

            }
        } catch (IOException io) {
            io.printStackTrace();

        }
    }
}
