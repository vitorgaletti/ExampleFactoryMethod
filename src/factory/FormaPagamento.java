
package factory;

public interface FormaPagamento {
    
    String sendMessage(String mensagem);
    
    void processos(Double valorTotal, Double valorPago, String fPagamento);
       
}
