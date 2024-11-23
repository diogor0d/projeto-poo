public abstract class ProdutoAlimentar extends Produto {
    // Valores de taxas para Continente, Madeira e Acores
    protected boolean biologico;

    public ProdutoAlimentar(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.biologico = biologico;
    }
}
