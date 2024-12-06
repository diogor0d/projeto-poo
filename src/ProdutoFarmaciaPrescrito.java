public class ProdutoFarmaciaPrescrito extends Produto {

    private final String medico;
    protected double[] taxas = {0.06, 0.05, 0.04};

    public ProdutoFarmaciaPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, String medico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.medico = medico;
    }

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
