import java.util.ArrayList;

/**
 * Classe que representa um produto alimentar de taxa reduzida
 */
public class ProdutoAlimentarTR extends ProdutoAlimentar {
    /**
     * Lista de certificações do produto
     */
    private final ArrayList<Certificacao> certificacoes;
    /**
     * Taxas de IVA de um produto alimentar de taxa reduzida para cada localização.
     */
    protected double[] taxas = {0.06, 0.05, 0.04};
    /**
     * Desconto para produtos biológicos.
     */
    protected double descontoBiologico = 0.1;
    /**
     * Desconto para produtos com 4 certificações.
     */
    protected double descontoCertificacoes = 0.99;

    /**
     * Construtor da classe ProdutoAlimentarTR
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param descricao Descrição do produto
     * @param quantidade Quantidade do produto
     * @param preco Preço do produto
     * @param biologico Indica se o produto é biológico
     * @param certificacoes Lista de certificações do produto
     */
    public ProdutoAlimentarTR(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico, ArrayList<Certificacao> certificacoes) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.certificacoes = certificacoes;
    }

    /**
     * Método que calcula o IVA de um produto alimentar
     * @param cliente Cliente que está a comprar o produto, cuja localização influencia o valor do imposto a aplicar
     * @return Valor da Taxa do IVA do produto
     */
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

    /**
     * Método que devolve a representação textual de um produto alimentar de taxa reduzida. Na prática apenas acrescenta a lista de certificações do produto ao método toString da superclasse.
     * @return Representação textural do ProdtuoAlimentarTR
     */
    @Override
    public String toString() {
        return super.toString() + " CRT:" + certificacoes;
    }
}
