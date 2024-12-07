/**
 * Classe abstrata que representa um produto de farmácia.
 */
public abstract class ProdutoFarmacia extends Produto {
    /**
     * Taxas para produtos de farmácia em cada localização.
     */
    protected double[] taxas = {0.23, 0.23, 0.23};

    /**
     * Construtor da classe ProdutoFarmacia.
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param descricao Descrição do produto
     * @param quantidade Quantidade do produto
     * @param preco Preço do produto
     */
    public ProdutoFarmacia(int codigo, String nome, String descricao, int quantidade, double preco) {
        super(codigo, nome, descricao, quantidade, preco);
    }
}
