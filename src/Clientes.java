import java.util.ArrayList;
import java.util.Scanner;

public class Clientes extends Cliente {
    private ArrayList<Cliente> listaClientes;

    // Construtor da classe Clientes
    public Clientes() {
        super("", 0, "");
        this.listaClientes = new ArrayList<>();
    }

    // Método para ler os dados e adicionar o cliente
    public void novoCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o número de contribuinte: ");
        int contribuinte = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n" pendente

        System.out.print("Digite a localização do cliente: ");
        String localizacao = scanner.nextLine();

        adicionarCliente(nome, contribuinte, localizacao);
    }

    // Método para adicionar um novo cliente à lista
    public void adicionarCliente(String nome, int contribuinte, String localizacao) {
        Cliente novoCliente = new Cliente(nome, contribuinte, localizacao);
        listaClientes.add(novoCliente);
        System.out.println("Novo cliente, " +nome+", adicionado com sucesso!");
    }

    // Método para listar os clientes
    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : listaClientes) {
                System.out.println("Nome: " + cliente.getNome()
                        + ", Contribuinte: " + cliente.getContribuinte()
                        + ", Localização: " + cliente.getLocalizacao());
            }
        }
    }

    public void editarCliente(){
        Scanner scanner = new Scanner(System.in);
        if (!listaClientes.isEmpty()) {
            System.out.print("Qual é o nome do cliente, ao qual quer alterar os dados? ");
            String nome = scanner.nextLine();
            for (Cliente cliente : listaClientes) {
                if (cliente.getNome().equalsIgnoreCase(nome)) {
                    System.out.print("Cliente " + nome + " encontrado.");
                    int opcao = -1;

                    while (opcao != 0) {
                        System.out.print("\nQue dados deseja alterar?\n1- Nome\n2- Contribuinte\n3- Localização\n0- Cancelar\nOpção-> ");
                        opcao = scanner.nextInt();
                        scanner.nextLine();  // Limpar o buffer do scanner
                        switch (opcao) {
                            case 1:
                                System.out.print("Novo nome: ");
                                String novoNome = scanner.nextLine();
                                cliente.setNome(novoNome);  // Atualiza o nome
                                System.out.println("Nome alterado com sucesso.");
                                break;
                            case 2:
                                System.out.print("Novo número de contribuinte: ");
                                int novoContribuinte = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer
                                cliente.setContribuinte(novoContribuinte);  // Atualiza o contribuinte
                                System.out.println("Contribuinte alterado com sucesso.");
                                break;
                            case 3:
                                System.out.print("Nova localização: ");
                                String novaLocalizacao = scanner.nextLine();
                                cliente.setLocalizacao(novaLocalizacao);  // Atualiza a localização
                                System.out.println("Localização alterada com sucesso.");
                                break;
                            case 0:
                                System.out.println("Alteração cancelada.");
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                        System.out.print("Deseja alterar mais algum dado? (S ou N): ");
                        String continuar = scanner.nextLine();
                        if (continuar.equalsIgnoreCase("N")) {
                            break;
                        }
                    }
                }
                System.out.println("Cliente não encontrado.");
            }
        }
        else {
                System.out.println("A lista de clientes está vazia.");
            }
    }
}
