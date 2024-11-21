public class ProdutoAlimentarTN extends ProdutoAlimentar {

    protected double[] taxas = {13, 12, 9};

    public ProdutoAlimentarTN(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
    }
}
