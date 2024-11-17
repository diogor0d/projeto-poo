public class ProdutoFarmaciaPrescrito extends Produto {

    private String medico;
    protected double[] taxas = {6,5,4};

    public ProdutoFarmaciaPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, double[] taxas, String medico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.taxas = taxas;
        this.medico = medico;
    }
}
