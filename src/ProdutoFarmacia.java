public class ProdutoFarmacia extends Produto{
    private CategoriaFarmacia categoria;
    protected double[] taxas = {23,23,23};

    public ProdutoFarmacia(int codigo, String nome, String descricao, int quantidade, double preco, double[] taxas, CategoriaFarmacia categoria) {
        super(codigo, nome, descricao, quantidade, preco);
        this.taxas = taxas;
        this.categoria = categoria;
    }
}
