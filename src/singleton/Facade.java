package singleton;


import pojo.Roupa;

public class Facade {

   
    private CarrinhoDeRoupas carrinho;
    private Biblioteca biblioteca;

    public Facade() {
        carrinho = CarrinhoDeRoupas.getInstance();
        biblioteca = Biblioteca.getInstance();
    }

    public void cadastrarNovaRoupa(Roupa roupa) {
        carrinho.addRoupas(roupa);

    }

    public void consultarRoupa(String pesq) {
        carrinho.consultarRoupa(pesq);
    }

    public String cobrar() {
        biblioteca.listaRoupas = carrinho.getListRoupas();
        return biblioteca.precoTotal();
    }

    public void pagamento(int formaDePagamento) {
        biblioteca.realizarPagamento(formaDePagamento);
    }

    public void removerRoupa(int id) {
        carrinho.removeRoupa(id);
    }

    public void limparCarrinho(String resp) {
        carrinho.limparCarrinho(resp);
    }

}
