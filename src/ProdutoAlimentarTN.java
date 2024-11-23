public class ProdutoAlimentarTN extends ProdutoAlimentar {

    protected double[] taxas = {0.23, 0.22, 0.16};
    protected double descontoBiologico = 0.1;

    public ProdutoAlimentarTN(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
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
        return iva;
    }
}
