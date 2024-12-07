/**
 * Classe que representa um produto alimentar.
 */
public class ProdutoAlimentarTI extends ProdutoAlimentar {
    /**
     * Categoria alimentar do produto.
     */
    private CategoriaAlimentar categoria;
    /**
     * Taxas de IVA de um produto alimentar de taxa intermédia para cada localização.
     */
    protected double[] taxas = {0.13, 0.12, 0.09};
    /**
     * Desconto de para produtos biológicos.
     */
    protected double descontoBiologico = 0.1;
    /**
     * Acréscimo de IVA para produtos da categoria vinho.
     */
    protected double acrescimoVinho = 0.01;

    /**
     * Construtor da classe ProdutoAlimentarTI.
     * @param codigo Código do produto.
     * @param nome Nome do produto.
     * @param descricao Descrição do produto.
     * @param quantidade Quantidade do produto.
     * @param preco Preço do produto.
     * @param biologico Indica se o produto é biológico.
     * @param categoriaAlimentar Categoria alimentar do produto.
     */
    public ProdutoAlimentarTI(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico, CategoriaAlimentar categoriaAlimentar) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.categoria = categoriaAlimentar;
    }

    /**
     * Método que calcula o IVA de um produto alimentar.
     * @param cliente Cliente que está a comprar o produto, cuja localização influencia o valor do impossto a aplicar
     * @return Valor da Taxa do IVA do produto
     */
    @Override
    public double calcularIva(Cliente cliente) {
        double iva = 0;
        switch (cliente.getLocalizacao()) {
            case "Continente" -> iva = taxas[0];
            case "Madeira" -> iva = taxas[1];
            case "Açores" -> iva = taxas[2];
        }

        if (biologico) {
            iva -= descontoBiologico;
        }

        if (categoria == CategoriaAlimentar.VINHO) {
            iva += acrescimoVinho;
        }

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
