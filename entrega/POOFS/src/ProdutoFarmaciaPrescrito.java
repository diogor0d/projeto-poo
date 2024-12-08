/**
 * Classe que representa um produto de farmácia prescrito.
 */
public class ProdutoFarmaciaPrescrito extends Produto {
    /**
     * Médico que prescreveu o produto de farmacia.
     */
    private final String medico;
    /**
     * Taxas para produtos de farmácia prescritos em cada localização.
     */
    protected double[] taxas = {0.06, 0.05, 0.04};

    /**
     * Construtor da classe ProdutoFarmaciaPrescrito.
     *
     * @param codigo     Código do produto.
     * @param nome       Nome do produto.
     * @param descricao  Descrição do produto.
     * @param quantidade Quantidade do produto.
     * @param preco      Preço do produto.
     * @param medico     Médico que prescreveu o produto.
     */
    public ProdutoFarmaciaPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, String medico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.medico = medico;
    }

    /**
     * Método que calcula o IVA de um produto de farmácia.
     *
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

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
