import java.util.ArrayList;
import java.util.Scanner;

public class Produtos {
    private ArrayList<Produto> produtos;

    // Construtor da classe Produtos
    public Produtos() {
        this.produtos = new ArrayList<>();
    }

    public ArrayList<Produto> getListaProdutos() {
        return this.produtos;
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
            return null;
        } else {
            for (Produto produto : produtos) {
                if (produto.getNome().equalsIgnoreCase(nome)) {
                    return produto;
                }
            }
            System.out.println("Produto não encontrado");
            return null;
        }
    }

    public boolean removerProduto(ArrayList<Produto> listaProdutos, Produto produto) {
        if (listaProdutos.contains(produto)) {
            listaProdutos.remove(produto);
            return true;
        } else {
            return false;
        }
    }


}