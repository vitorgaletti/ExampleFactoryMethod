package factory;

public class FactoryMethod {

    public FormaPagamento create(int formaDePagamento) {

        if (formaDePagamento == 1) {
            return new Dinheiro();
        } else if (formaDePagamento == 2) {
            return new Boleto();
        } else if (formaDePagamento == 3) {
            return new CartaoCredito();
        } else {
            throw new IllegalArgumentException("A forma de Pagamento está incorreto");
        }
    }

}
