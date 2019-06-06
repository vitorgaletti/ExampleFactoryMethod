
package pojo;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="roupa")
public class Roupa {
    
    @Attribute(name = "id", required = false)
    private int id;
    @Element(name = "descricao", required = false)
    private String descricao;
    @Element(name = "marca", required = false)
    private String marca;
    @Element(name = "tamanho", required = false)
    private String tamanho;
    @Element(name = "cor", required = false)
    private String cor;
    @Element(name = "quantidade", required = false)
    private int quantidade;
    @Element(name = "preco", required = false)
    private double preco;  
    @ElementList(inline = true, required = false)
    private List<Roupa> roupa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public List<Roupa> getList() {
        return roupa;
    }

    public void setList(List<Roupa> list) {
        this.roupa = list;
    }
  
    @Override
    public String toString() {
        return "ID: " + id +  "\nDescrição: " + descricao + "\nMarca: " + marca + "\nTamanho: " + tamanho + "\nCor: " + cor + "\nQuantidade: " + quantidade + "\nPreço: " + preco;
    }
        
}
