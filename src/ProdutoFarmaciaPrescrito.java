public class ProdutoFarmaciaPrescrito extends Produto {

    private String medico;
    protected double[] taxas = {0.06, 0.05, 0.04};

    public ProdutoFarmaciaPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, String medico) {
        super(codigo, nome, descricao, quantidade, preco);
        this.medico = medico;
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

        if (iva < 0) {
            iva = 0;
        }

        return iva;
    }
}
