import java.util.ArrayList;

/**
 * Classe que representa uma lista de produtos e todos os métodos de manipulação de grupos de produtos.
 */
public class Produtos {
    /**
     * Lista de produtos.
     */
    private ArrayList<Produto> produtos;

    /**
     * Construtor da classe Produtos.
     */
    public Produtos() {
        this.produtos = new ArrayList<>();
    }

    /**
     * Método para obter a lista de produtos.
     */
    public ArrayList<Produto> getListaProdutos() {
        return this.produtos;
    }

    /**
     * Método para definir a lista de produtos.
     * @param novaListaProdutos Nova lista de produtos.
     */
    public void setListaProdutos(ArrayList<Produto> novaListaProdutos) {
        if (novaListaProdutos != null) {
            this.produtos = novaListaProdutos;
            System.out.println("%sLista de Produtos atualizada.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
        } else {
            System.out.println("%sA nova lista de proutos é inválida.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
        }
    }

    /**
     * Método para adicionar um produto à lista de produtos.
     * @param produto Produto a adicionar.
     */
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println(Formatacao.YELLOW.getCode() + "Novo produto " + produto + " adicionado!" + Formatacao.RESET.getCode());
    }

    /**
     * Método para listar todos os produtos presentes na lista.
     * @param produtos Lista de produtos.
     */
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

    /**
     * Método para procurar um produto pelo seu código.
     * @param codigo
     * @return produto ou null caso não exista
     */
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