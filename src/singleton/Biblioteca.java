package singleton;

import factory.FactoryMethod;
import factory.FormaPagamento;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pojo.Roupa;

public class Biblioteca {

    private static Biblioteca instance;
    public static ArrayList<Roupa> listaRoupas;
    public static double total = 0;
    Scanner input = new Scanner(System.in);

    private Biblioteca() {
        listaRoupas = new ArrayList<Roupa>();
    }

    public static Biblioteca getInstance() {
        if (Biblioteca.instance == null) {
            Biblioteca.instance = new Biblioteca();
        }
        return Biblioteca.instance;
    }

    public String precoTotal() {

        total = 0;
        
        for (int i = 0; i < listaRoupas.size(); i++) {
            total += listaRoupas.get(i).getPreco();

        }
        if (listaRoupas.isEmpty()) {
            return "\nNenhuma Roupa para Pagar!";
        }
        return "Valor a ser pago pelo cliente: R$" + String.format("%.2f", total);
    }

    public void realizarPagamento(int formaDePagamento) {

        if (total == 0) {
            System.out.println("\nExecutar cobrança primeiro.");
        } else if (formaDePagamento == 1) {
            System.out.print("\nDigite o valor para pagar:  ");
            Double valorPago = input.nextDouble();
            FormaPagamento form = new FactoryMethod().create(formaDePagamento);
            form.processos(total, valorPago, "Dinheiro");

        } else if (formaDePagamento == 2) {
            FormaPagamento form = new FactoryMethod().create(formaDePagamento);
            form.processos(total, 0.0, "Boleto");

        } else if (formaDePagamento == 3) {
            FormaPagamento form = new FactoryMethod().create(formaDePagamento);
            form.processos(total, 0.0, "CartaoDeCredito");
            System.out.println(form.sendMessage(""));

        } else if (formaDePagamento <= 0 || formaDePagamento > 3) {
            System.out.println("\nNúmero Inválido!! ");

        }

    }

    public void ExportXML(String fPagamento) throws IOException {

        final String codigos = "id";
        final String precoTotal = "precototal";
        final String fDePagamento = "formaDePagamento";

        FileWriter fw = new FileWriter("src/pojo/roupasCompradas.xml");
        StringBuilder db = new StringBuilder();

        db.append("<ROUPASCOMPRADAS>");

        for (int i = 0; i < listaRoupas.size(); i++) {
            db.append("<ROUPASCOMPRADAS>");
            db.append("<" + codigos + ">" + listaRoupas.get(i).getId() + "</" + codigos + ">");
            db.append("<" + precoTotal + ">" + String.format("%.2f", total) + "</" + precoTotal + ">");
            db.append("<" + fDePagamento + ">" + fPagamento + "</" + fDePagamento + ">"
            );
            db.append("</ROUPASCOMPRADAS>");
        }

        db.append("</ROUPASCOMPRADAS>");
        fw.write(db.toString());
        fw.flush();
        fw.close();

        listaRoupas.removeAll(listaRoupas);
        total = 0;

        System.out.println("\n\nO Arquivo roupasCompradas.xml foi Atualizado");

    }

}
