//duvida: linha 32; (metodos getdata, getprodutos e setprodutos ainda nao tao a ser utilizados)

import java.util.ArrayList;
import java.io.Serializable;

public class Fatura implements Serializable {
    private int num;
    private Cliente cliente;
    private Data data;
    private ArrayList<Produto> produtos;

    // Construtor
    public Fatura(int num, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        this.num = num;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    public double calcularTotalBruto() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidade();
        }

        return total;
    }

    // Getters e Setters
    public int getNum() {
        return num;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Data getData() {
        return data;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setNumero(int numero) {
        this.num = numero;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    // toString
    //duvida só estou a imprimir o nome dos produtos, mas nao os produtos todos para "facilitar" a leitura, o que achas? depois para guardar no ficheiro, atraves do nome do produto,
    // recuperamos o produto inteiro na lista de produtos
    public String toString() {
        String[] produtosArray = new String[produtos.size()];
        for (int i = 0; i < produtos.size(); i++) {
            produtosArray[i] = produtos.get(i).getNome();
        }
        // Usar String.join para unir os nomes dos produtos com vírgulas
        String produtosConcatenados = String.join(", ", produtosArray);

        return String.format(
            "| Fatura: %-5s  Cliente: %-20s  Contribuinte: %-8s  Localização: %-13s |  Data: %-12s  Produtos: %-55s |",
                num, cliente.getNome(), cliente.getContribuinte(), cliente.getLocalizacao(), data, produtosConcatenados
            );
        }


        public String toStringParaFicheiro() {
        String[] produtosArray = new String[produtos.size()];
        for (int i = 0; i < produtos.size(); i++) {
            produtosArray[i] = produtos.get(i).getNome();
        }
        // Usar String.join para unir os nomes dos produtos com vírgulas
        String produtosConcatenados = String.join(", ", produtosArray);

        return num + ", " + cliente.getContribuinte() + ", " + data + ", " + produtosConcatenados;
    }

}
