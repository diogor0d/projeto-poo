/**
 * Classe que representa um produto alimentar de tipo normal.
 */
public class ProdutoAlimentarTN extends ProdutoAlimentar {
    /**
     * Taxas de IVA de um produto alimentar de tipo normal para cada localização.
     */
    protected double[] taxas = {0.23, 0.22, 0.16};
    /**
     * Desconto de para produtos biológicos.
     */
    protected double descontoBiologico = 0.1;

    /**
     * Construtor da classe ProdutoAlimentarTN.
     * @param codigo Código do produto.
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param quantidade Quantidade do produto.
     * @param preco Preço do produto.
     * @param biologico Indica se o produto é biológico.
     */
    public ProdutoAlimentarTN(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
    }

    /**
     * Método que calcula o IVA de um produto alimentar.
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

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
