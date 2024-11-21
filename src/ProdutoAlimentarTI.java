public class ProdutoAlimentarTI extends ProdutoAlimentar {

    private CategoriaAlimentar categoriaAlimentar;
    protected double[] taxas = {13, 12, 9};

    public ProdutoAlimentarTI(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico, CategoriaAlimentar categoriaAlimentar) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.categoriaAlimentar = categoriaAlimentar;
    }
}
