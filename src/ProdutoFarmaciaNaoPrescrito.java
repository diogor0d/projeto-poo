public class ProdutoFarmaciaNaoPrescrito extends ProdutoFarmacia {
    protected CategoriaFarmacia categoria;
    public ProdutoFarmaciaNaoPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, boolean receita, CategoriaFarmacia categoria) {
        super(codigo, nome, descricao, quantidade, preco);
        this.categoria = categoria;
    }
}
