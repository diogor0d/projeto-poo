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
        double iva = switch (cliente.getLocalizacao()) {
            case "Continente" -> taxas[0];
            case "Madeira" -> taxas[1];
            case "Açores" -> taxas[2];
            default -> 0;
        };

        if (biologico) {
            iva -= descontoBiologico;
        }

        if (certificacoes.size() == 4) {
            iva -= descontoCertificacoes;
        }

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }

    @Override
    public String toString() {
        return super.toString() + " CRT:" + certificacoes;
    }
}
