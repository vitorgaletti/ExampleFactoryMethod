package singleton;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import pojo.Roupa;

public class Main implements Observer {

    public static CarrinhoDeRoupas carrinhoUpdate;

    public static void main(String[] args) {

        CarrinhoDeRoupas carrinho = CarrinhoDeRoupas.getInstance();
        Main observer = new Main();
        carrinho.addObserver(observer);
        boolean verificarRoupa = false;
        int op;
        Serializer serializer = new Persister();

        do {
            System.out.println("\n-------------------- MENU DE ROUPAS -------------------------");

            System.out.println("\n 1 - Cadastrar uma Nova Roupa ao Carrinho ");
            System.out.println("\n 2 - Lista de Roupas já Cadastradas ");
            System.out.println("\n 3 - Verificar a Quantidade de Roupas Disponível");
            System.out.println("\n 4 - Consultar todas as Roupas do Carrinho ");
            System.out.println("\n 5 - Consultar uma Roupa ");
            System.out.println("\n 6 - Cobrar ");
            System.out.println("\n 7 - Realizar Pagamento ");
            System.out.println("\n 8 - Remover uma Roupa ");
            System.out.println("\n 9 - Limpar Carrinho ");
            System.out.println("\n 10 - Sair do Menu ");

            Scanner input = new Scanner(System.in);
            System.out.print("\n\nDigite um número para escolher uma opção do MENU: ");
            op = input.nextInt();

            switch (op) {

                case 1:
                    System.out.println("\n-------------------- CADASTRAR UMA NOVA ROUPA AO CARRINHO -------------------------\n");

                    Roupa roupas = new Roupa();
                    Scanner scanCadastrar = new Scanner(System.in);

                    System.out.print("\nDigite uma Descrição: ");
                    String descricao = input.next();
                    roupas.setDescricao(descricao);

                    System.out.print("\nDigite uma Marca: ");
                    String marca = input.next();
                    roupas.setMarca(marca);

                    System.out.print("\nDigite um Tamanho: ");
                    String tamanho = input.next();
                    roupas.setTamanho(tamanho);

                    System.out.print("\nDigite uma Cor: ");
                    String cor = input.next();
                    roupas.setCor(cor);

                    System.out.print("\nDigite a Quantidade: ");
                    int quantidade = input.nextInt();
                    roupas.setQuantidade(quantidade);
                    new Facade().cadastrarNovaRoupa(roupas);

                    System.out.print("\nPressione ENTER para continuar.");
                    scanCadastrar.nextLine();
                    break;

                case 2:
                    System.out.println("\n-------------------- LISTA DE ROUPAS JÁ CADASTRADAS -------------------------\n");

                    Scanner scanListRoupas = new Scanner(System.in);

                    verificarRoupa = false;

                    File source = new File("src/pojo/roupas.xml");

                    try {
                        Roupa roupa = serializer.read(Roupa.class, source);

                        for (int i = 0; i < roupa.getList().size(); i++) {
                            System.out.println("\n----------------------------------------------------------------");
                            System.out.println("ID: " + roupa.getList().get(i).getId());
                            System.out.println("Descrição: " + roupa.getList().get(i).getDescricao());
                            System.out.println("Marca: " + roupa.getList().get(i).getMarca());
                            System.out.println("Tamanho: " + roupa.getList().get(i).getTamanho());
                            System.out.println("Cor: " + roupa.getList().get(i).getCor());
                            System.out.println("Quantidade Disponível: " + roupa.getList().get(i).getQuantidade());
                            System.out.println("Preço: " + roupa.getList().get(i).getPreco());

                        }
                        System.out.print("\nInforme o ID da Roupa para adicionar ao Carrinho:  ");
                        int idRoupa = input.nextInt();

                        for (int i = 0; i < roupa.getList().size(); i++) {

                            while (roupa.getList().get(i).getId() == idRoupa) {
                                Object roupaCadastrada = roupa.getList().get(i);
                                new Facade().cadastrarNovaRoupa((Roupa) roupaCadastrada);
                                verificarRoupa = roupa.getList().get(i).getId() == idRoupa;

                                break;
                            }

                        }
                        if (!verificarRoupa) {
                            System.out.println("\nRoupa não Encontrada.");

                        }
                        System.out.printf("\nPressione ENTER para continuar.");
                        scanListRoupas.nextLine();
                    } catch (Exception ex) {
                        System.out.printf("Erro: " + ex);
                    }

                    break;

                case 3:

                    System.out.println("\n-------------------- CARRINHO DE ROUPAS -------------------------\n");

                    Scanner scanVerficar = new Scanner(System.in);

                    System.out.print("\nQuantidade de Roupas Disponível: " + carrinho.getListRoupas().size());

                    System.out.print("\n\nPressione ENTER para continuar.");
                    scanVerficar.nextLine();
                    break;

                case 4:

                    System.out.println("\n-------------------- CARRINHO DE ROUPAS -------------------------\n");

                    System.out.print("\nQuantidade de Roupas Disponível: " + carrinho.getListRoupas().size() + "\n");

                    Scanner scanCarrinho = new Scanner(System.in);

                    if (carrinho.getListRoupas().isEmpty()) {
                        System.out.print("\nSeu carrinho está Vazio!! \nPressione ENTER para continuar. ");
                        scanCarrinho.nextLine();
                    } else {
                        carrinho.toString();
                        System.out.print("\nPressione ENTER para continuar. ");
                        scanCarrinho.nextLine();

                    }
                    break;

                case 5:

                    System.out.println("\n-------------------- CONSULTAR UMA ROUPA -------------------------\n");
                    Scanner scanConsulta = new Scanner(System.in);

                    if (!carrinho.getListRoupas().isEmpty()) {
                        System.out.print("\nDigite uma descrição da roupa para CONSULTAR:  ");
                        String pesq = input.next();
                        new Facade().consultarRoupa(pesq);

                    } else {
                        System.out.print("\nSeu Carrinho está Vazio !! ");
                        scanConsulta.nextLine();
                        break;
                    }

                    System.out.print("\nPressione ENTER para continuar. ");
                    scanConsulta.nextLine();
                    break;

                case 6:

                    System.out.println("\n-------------------- COBRANÇA -------------------------\n");
                    Scanner scanCobrar = new Scanner(System.in);
                    System.out.println(new Facade().cobrar());
                    System.out.print("\nPressione ENTER para continuar. ");
                    scanCobrar.nextLine();
                    break;

                case 7:
                    System.out.println("\n-------------------- PAGAMENTO -------------------------\n");
                    Scanner scanPagamento = new Scanner(System.in);

                    System.out.println("\n-------- Formas de Pagamento --------\n");
                    System.out.println("1 - DINHEIRO ");
                    System.out.println("\n2 - BOLETO ");
                    System.out.println("\n3 - CARTÃO DE CRÉDITO ");

                    System.out.print("\n\nDigite um número para escolher a forma de pagamento:  ");
                    int opcaoPagamento = input.nextInt();
                    new Facade().pagamento(opcaoPagamento);

                    System.out.print("\nPressione ENTER para continuar. ");
                    scanPagamento.nextLine();
                    break;

                case 8:
                    System.out.println("\n-------------------- REMOVER UMA ROUPA -------------------------\n");
                    Scanner scanRemover = new Scanner(System.in);
                    System.out.print("\nDigite o ID da Roupa para REMOVER:  ");
                    int id = input.nextInt();
                    new Facade().removerRoupa(id);
                    System.out.print("\nPressione ENTER para continuar. ");
                    scanRemover.nextLine();
                    break;

                case 9:
                    System.out.println("\n-------------------- LIMPAR CARRINHO -------------------------\n");
                    Scanner scanRemove = new Scanner(System.in);

                    if (carrinho.getListRoupas().isEmpty()) {
                        System.out.print("\nSeu Carrinho está Vazio !! ");
                        System.out.print("\nPressione ENTER para continuar. ");
                        scanRemove.nextLine();
                        break;
                    }

                    System.out.print("\nVocê realmente deseja limpar o carrinho? (sim) ou (nao) ");
                    String resp = input.next();
                    new Facade().limparCarrinho(resp);
                    System.out.print("\nPressione ENTER para continuar. ");
                    scanRemove.nextLine();
                    break;

                case 10:

                    System.out.print("\nVocê realmente deseja sair do MENU? (sim) ou (nao) ");
                    String sair = input.next();

                    if (sair.equals("sim")) {

                        return;
                    } else {

                        break;
                    }

                default:

                    Scanner scanInvalido = new Scanner(System.in);
                    System.out.print("\nNúmero Inválido!. Pressione ENTER para voltar ao MENU");
                    scanInvalido.nextLine();
                    break;
            }

        } while (op != 11 || op == 11);

    }

    @Override
    public void update(Observable observable, Object arg1) {
        carrinhoUpdate = (CarrinhoDeRoupas) observable;
        System.out.printf("\nCarrinho foi Atualizado.........");

    }

}
