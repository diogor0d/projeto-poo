//feita toda com o chat, para ja so serve para testar as restantes funcionalidades

import java.util.ArrayList;
import java.util.Scanner;

public class Produtos {
    private ArrayList<Produto> produtos;

    // Construtor da classe Produtos
    public Produtos() {
        this.produtos = new ArrayList<>();
    }


    // Método para adicionar um novo produto
    public void adicionarProduto(int codigo, String nome, String descricao, int quantidade, double preco){
        Produto produto = new Produto(codigo, nome, descricao, quantidade, preco);
        produtos.add(produto);
        System.out.println("Produto \"" + nome + "\" adicionado com sucesso!");
    }

    // Método para remover um produto pelo nome
    public boolean removerProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                produtos.remove(produto);
                System.out.println("Produto \"" + nome + "\" removido com sucesso!");
                return true;
            }
        }
        System.out.println("Produto \"" + nome + "\" não encontrado.");
        return false;
    }

    // Método para buscar um produto pelo nome
    public Produto buscarProduto(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        System.out.println("Produto \"" + nome + "\" não encontrado.");
        return null;
    }

    // Método para listar todos os produtos
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

    // Método para interagir com o usuário e adicionar produtos via entrada de dados
    public void entradaDeProdutos() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Digite o código do produto (ou 'sair' para finalizar): ");
            int codigo = scanner.nextInt();
            scanner.nextLine(); // Consumir o "\n" pendente

            System.out.print("Digite o nome do produto (ou 'sair' para finalizar): ");
            String nome = scanner.nextLine().trim();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Digite a descrição do produto (ou 'sair' para finalizar): ");
            String descricao = scanner.nextLine().trim();
            if (descricao.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.print("Digite a quantidade do produto: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir o "\n" pendente


            System.out.print("Digite o preço do produto: ");
            double preco = scanner.nextDouble();



            adicionarProduto(codigo, nome, descricao, quantidade, preco);
            scanner.close();
        }
    }
}