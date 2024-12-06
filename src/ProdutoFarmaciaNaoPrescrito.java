public class ProdutoFarmaciaNaoPrescrito extends ProdutoFarmacia {
    protected CategoriaFarmacia categoria;
    protected double descontoAnimais = 0.01;

    public ProdutoFarmaciaNaoPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, CategoriaFarmacia categoria) {
        super(codigo, nome, descricao, quantidade, preco);
        this.categoria = categoria;
    }

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
