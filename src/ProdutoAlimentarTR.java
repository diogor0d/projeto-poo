import java.util.ArrayList;

public class ProdutoAlimentarTR extends ProdutoAlimentar {

    private ArrayList<Certificacao> certificacoes;
    protected double[] taxas = {0.06, 0.05, 0.04};
    protected double descontoBiologico = 0.1;
    protected double descontoCertificacoes = 0.99;

    public ProdutoAlimentarTR(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico, ArrayList<Certificacao> certificacoes) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.certificacoes = certificacoes;
    }

    @Override
    public double calcularIva(Cliente cliente) {
        double iva = 0;

        switch (cliente.getLocalizacao()) {
            case "Continente":
                iva = taxas[0];
                break;
            case "Madeira":
                iva = taxas[1];
                break;
            case "Açores":
                iva = taxas[2];
                break;
        }
        if (biologico) {
            iva -= descontoBiologico;
        }

        if (certificacoes.size() == 4) {
            iva -= descontoCertificacoes;
        }

        return iva;
    }
}
