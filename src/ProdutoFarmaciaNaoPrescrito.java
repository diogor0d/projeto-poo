public class ProdutoFarmaciaNaoPrescrito extends ProdutoFarmacia {
    protected CategoriaFarmacia categoria;
    protected double descontoAnimais = 0.99;

    public ProdutoFarmaciaNaoPrescrito(int codigo, String nome, String descricao, int quantidade, double preco, CategoriaFarmacia categoria) {
        super(codigo, nome, descricao, quantidade, preco);
        this.categoria = categoria;
    }

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

        if (categoria == CategoriaFarmacia.ANIMAIS) {
            iva *= descontoAnimais;
        }

        return iva;
    }
}
