import java.util.ArrayList;

public class ProdutoAlimentarTR extends ProdutoAlimentar{

    private ArrayList<Certificacao> certificacoes;
    protected double[] taxas = {6,5,4};

    public ProdutoAlimentarTR(int codigo, String nome, String descricao, int quantidade, double preco, double[] taxas, boolean biologico, ArrayList<Certificacao> certificacoes) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.taxas = taxas;
        this.certificacoes = certificacoes;
    }
}
