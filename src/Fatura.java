import java.util.ArrayList;

class Fatura {
    private int num;
    private Cliente cliente;
    private Data data;
    private ArrayList<Produto> produtos;

    public Fatura(int num, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        this.num = num;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }
}
