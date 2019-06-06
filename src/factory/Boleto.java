package factory;

import java.awt.print.PrinterJob;
import java.util.Random;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import singleton.Biblioteca;

public class Boleto implements FormaPagamento {

    @Override
    public String sendMessage(String mensagem) {
        return "";
    }

    @Override
    public void processos(Double valorTotal, Double valorPago, String fPagamento) {
        Random r = new Random();
        String codigoBoleto = Integer.toString(r.nextInt());

        try {

            /*
             Precisa ter a Biblioteca Barbecue para criar o código de barra.
             Download : https://sourceforge.net/projects/barbecue/files/barbecue/1.5-beta1/
             */
            Barcode barcode = BarcodeFactory.createCode128A(codigoBoleto);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPrintable(barcode);
            System.out.println("\nGerando Boleto..........");

            if (printerJob.printDialog()) {

                printerJob.print();
                System.out.println("\n\nBoleto foi Gerado. Valor Total para Pagar:  " + valorTotal);
                System.out.println("\nA transação somente será liberada quando o boleto for pago no prazo de 3 dias úteis.");
                System.out.println("\nCaso contrário a compra será cancelada.");
                Biblioteca.getInstance().ExportXML(fPagamento);

            } else {
                printerJob.cancel();
                System.out.print("\nBoleto não foi Gerado!!");
            }

        } catch (Exception ex) {
            ex.getMessage();
        }

    }
}
