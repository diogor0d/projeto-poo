public class ProdutoAlimentarTI extends ProdutoAlimentar {

    private CategoriaAlimentar categoria;
    protected double[] taxas = {0.13, 0.12, 0.09};
    protected double descontoBiologico = 0.1;
    protected double acrescimoVinho = 0.01;

    public ProdutoAlimentarTI(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico, CategoriaAlimentar categoriaAlimentar) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
        this.categoria = categoriaAlimentar;
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

        if (categoria == CategoriaAlimentar.VINHO) {
            iva += acrescimoVinho;
        }

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
