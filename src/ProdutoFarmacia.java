public abstract class ProdutoFarmacia extends Produto {
    protected double[] taxas = {0.23, 0.23, 0.23};

    public ProdutoFarmacia(int codigo, String nome, String descricao, int quantidade, double preco) {
        super(codigo, nome, descricao, quantidade, preco);
    }
}
