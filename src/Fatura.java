//duvida: linha 32; (metodos getdata, getprodutos e setprodutos ainda nao tao a ser utilizados)

import java.util.ArrayList;

public class Fatura {
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

        return "Fatura: " + num + " , Cliente: " + cliente.getNome() + ", Contribuinte: " + cliente.getContribuinte() + ", Localização do cliente: " + cliente.getLocalizacao() + ", Data: " + data + ", Produtos: " + produtosConcatenados;
    }
}
