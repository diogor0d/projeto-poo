//feita com o chat, para testar as restantes funcionalidades, podes apagar a partir donde disse

import java.util.ArrayList;
import java.util.Scanner;

public class Produtos {
    private ArrayList<Produto> produtos;

    // Construtor da classe Produtos
    public Produtos() {
        this.produtos = new ArrayList<>();
    }


    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println("Produto " + produto + " adicionado!");
    }

    public void listarProdutos() {
        if (produtos.isEmpty()) {
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

}