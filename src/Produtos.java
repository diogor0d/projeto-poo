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
            System.out.println("Lista de produtos atualizada com sucesso.");
        } else {
            System.out.println("A nova lista de proutos é inválida (null).");
        }
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto " + produto + " adicionado!");
    }

    public void listarProdutos(ArrayList<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Lista de produtos:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    public Produto encontrarProdutoPeloNome(String nome) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    return produto;
                }
            }
            System.out.println("Produto não encontrado");
        }
        return null;
    }
}