public class ProdutoAlimentarTN extends ProdutoAlimentar {

    protected double[] taxas = {0.23, 0.22, 0.16};
    protected double descontoBiologico = 0.1;

    public ProdutoAlimentarTN(int codigo, String nome, String descricao, int quantidade, double preco, boolean biologico) {
        super(codigo, nome, descricao, quantidade, preco, biologico);
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

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
