package singleton;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import pojo.Roupa;

public class CarrinhoDeRoupas extends Observable {

    private static CarrinhoDeRoupas instance;
    public static ArrayList<Roupa> listaRoupas;
    boolean verificarRoupa = false;

    private CarrinhoDeRoupas() {
        listaRoupas = new ArrayList<Roupa>();
    }

    public static CarrinhoDeRoupas getInstance() {
        if (CarrinhoDeRoupas.instance == null) {
            CarrinhoDeRoupas.instance = new CarrinhoDeRoupas();
        }
        return CarrinhoDeRoupas.instance;
    }

    public ArrayList<Roupa> getListRoupas() {
        return this.listaRoupas;

    }

    public void addRoupas(Roupa roupa) {

        if (roupa.getId() == 0) {

            Random r = new Random();
            roupa.setId(r.nextInt(100));
            double valor = (int) ((Math.random() * 900) + 10000) / 100.0;
            roupa.setPreco(valor);
            listaRoupas.add(roupa);
            System.out.println("\n\nRoupa Cadastrada com Sucesso!");
            notificar();
        } else {
            listaRoupas.add(roupa);
            System.out.println("\n\nRoupa Inserida no Carrinho com Sucesso!");
            notificar();

        }

    }

    @Override
    public String toString() {
        String result = "+";
        for (int i = 0; i < listaRoupas.size(); i++) {
            result = listaRoupas.get(i).toString();
            System.out.println("\n" + result);
        }
        return result;

    }

    public void consultarRoupa(String pesq) {

        for (int i = 0; i < listaRoupas.size(); i++) {
            while (listaRoupas.get(i).getDescricao().equalsIgnoreCase(pesq)) {

                System.out.print("\n" + listaRoupas.get(i).toString() + "\n");

                verificarRoupa = listaRoupas.get(i).getDescricao().equals(pesq);
                break;
            }

        }
        if (!verificarRoupa) {
            System.out.print("\nRoupa não Encontrada!! ");

        }

    }

    public void removeRoupa(int id) {
        if (listaRoupas.isEmpty()) {
            System.out.println("\nCarrinho Vázio!!");
        } else {
            for (int i = 0; i < listaRoupas.size(); i++) {
                if ((listaRoupas.get(i)).getId() == id) {
                    listaRoupas.remove(i);
                    notificar();
                    System.out.println("\nRoupa Removida com Sucesso!");
                } else {
                    System.out.println("\nRoupa não encontrada!!");
                }
            }

        }

    }

    public void limparCarrinho(String resp) {

        if (resp.equals("sim")) {
            for (int i = 0; i < listaRoupas.size(); i++) {
                while (!listaRoupas.isEmpty()) {
                    listaRoupas.remove(i);
                }
            }
            notificar();
            System.out.println("\nTodas as Roupas do Carrinho foram Removidas!");

        } else {
            System.out.println("\nTodas as Roupas permaneceram no Carrinho!");
        }

    }

    public void notificar() {
        setChanged();
        notifyObservers();

    }

}
