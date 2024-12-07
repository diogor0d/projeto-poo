import java.util.ArrayList;

public class Produtos {
    private ArrayList<Produto> produtos;

    // Construtor da classe Produtos
    public Produtos() {
        this.produtos = new ArrayList<>();
    }

    public ArrayList<Produto> getListaProdutos() {
        return this.produtos;
    }

    // Metodo para tornar uma lista de clientes na lista de clientes
    public void setListaProdutos(ArrayList<Produto> novaListaProdutos) {
        if (novaListaProdutos != null) {
            this.produtos = novaListaProdutos;
            System.out.println("%sLista de Produtos atualizada.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
        } else {
            System.out.println("%sA nova lista de proutos é inválida.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
        }
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println(Formatacao.YELLOW.getCode() + "Novo produto " + produto + " adicionado!" + Formatacao.RESET.getCode());
    }

    public void listarProdutos(ArrayList<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("%sNenhum produto registado.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
        } else {
            System.out.println("%s────────────────────────────────────────────────────────────────────────────%s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
            System.out.println(" Lista de produtos:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
            System.out.println("%s────────────────────────────────────────────────────────────────────────────%s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        }
    }

    public Produto procurarProdutoCodigo(int codigo) {
        if (!produtos.isEmpty()) {
            for (Produto produto : produtos) {
                if (produto.getCodigo() == codigo) {
                    return produto;
                }
            }
        }
        return null;
    }
}