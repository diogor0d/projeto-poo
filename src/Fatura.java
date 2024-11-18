import java.util.ArrayList;

class Fatura {
    private int num;
    private String nome_cliente;
    private Data data;
    private ArrayList<Produto> produtos;

    public Fatura(int num, String nome_cliente, Data data, ArrayList<Produto> produtos) {
        this.num = num;
        this.nome_cliente = nome_cliente;
        this.data = data;
        this.produtos = produtos;
    }

    // Getters e Setters
    public int getNumero() { return num; }
    public String getNomeCliente() { return nome_cliente; }
    public Data getData() { return data; }
    public  ArrayList<Produto> getProdutos() { return produtos; }

    public void setNumero(int numero) { this.num = numero; }
    public void setNomeCliente(String nomeCliente) { this.nome_cliente = nomeCliente; }
    public void setData(Data data) { this.data = data; }
    public void setProdutos(ArrayList<Produto> produtos) { this.produtos = produtos; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura Número: ").append(num)
                .append("\nCliente: ").append(nome_cliente)
                .append("\nData: ").append(data)
                .append("\nProdutos: ");

        // Lista os produtos
        for (Produto produto : produtos) {
            sb.append(produto.getNome()).append(", ");
        }

        return sb.toString();
    }
}
