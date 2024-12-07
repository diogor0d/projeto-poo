/**
 * Classe ProdutoFarmaciaNaoPrescrito que representa um produto de farmácia normal
 */
public class ProdutoFarmaciaNaoPrescrito extends ProdutoFarmacia {
    /**
     * Categoria do produto de farmácia
     */
    protected CategoriaFarmacia categoria;
    /**
     * Desconto para produtos da categoria animais
     */
    protected double descontoAnimais = 0.01;

    /**
     * Construtor da classe ProdutoFarmaciaNaoPrescrito.
     * @param codigo Código do produto.
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param quantidade Quantidade do produto.
     * @param preco Preço do produto.
     * @param categoria Categoria do produto.
     */
    public ProdutoFarmaciaNaoPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, CategoriaFarmacia categoria) {
        super(codigo, nome, descricao, quantidade, preco);
        this.categoria = categoria;
    }

    /**
     * Método que calcula o IVA de um produto de farmácia.
     * @param cliente Cliente que está a comprar o produto, cuja localização influencia o valor do imposto a aplicar
     * @return Valor da Taxa do IVA do produto
     */
    public double calcularIva(Cliente cliente) {
        double iva = switch (cliente.getLocalizacao()) {
            case "Continente" -> taxas[0];
            case "Madeira" -> taxas[1];
            case "Açores" -> taxas[2];
            default -> 0;
        };

        if (categoria == CategoriaFarmacia.ANIMAIS) {
            iva -= descontoAnimais;
        }

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
