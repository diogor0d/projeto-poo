/**
 * Classe que representa um produto alimentar.
 */
public abstract class ProdutoAlimentar extends Produto {
    /**
     * Indica se o produto é biológico.
     */
    protected boolean biologico;

    /**
     * Construtor da classe ProdutoAlimentar.
     *
     * @param codigo     Código do produto.
     * @param nome       Nome do produto.
     * @param descricao  Descrição do produto.
     * @param quantidade Quantidade do produto.
     * @param preco      Preço do produto.
     * @param biologico  Indica se o produto é biológico.
     */
    public ProdutoAlimentar(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.biologico = biologico;
    }
}
