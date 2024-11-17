public class ProdutoAlimentar extends Produto {
    // Valores de taxas para Continente, Madeira e Acores
    protected double[] taxas = {23,22,16};

    public ProdutoAlimentar(int codigo, String nome, String descricao, int quantidade, double preco, double[] taxas) {
        super(codigo, nome, descricao, quantidade, preco);
        this.taxas = taxas;
    }
}
