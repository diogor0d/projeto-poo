import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta classe representa uma lista de clientes e todas as ferraentas de gestão da mesma.
 */
public class Clientes {
    /**
     * Lista de clientes.
     */
    private ArrayList<Cliente> listaClientes;

    /**
     * Construtor da classe Clientes.
     */
    public Clientes() {
        this.listaClientes = new ArrayList<>();
    }

    /**
     * Método que devolve a lista de clientes.
     *
     * @return Lista de clientes.
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * Método que define a lista de clientes.
     *
     * @param novaListaClientes Nova lista de clientes.
     */
    public void setListaClientes(ArrayList<Cliente> novaListaClientes) {
        if (novaListaClientes != null) {
            this.listaClientes = novaListaClientes;
            System.out.println("%sLista de Clientes atualizada.%s".formatted(Formatacao.GREEN.getCode(),Formatacao.RESET.getCode()));
        } else {
            System.out.println("%s● A nova lista de clientes é inválida (null).%s".formatted(Formatacao.RED.getCode(),Formatacao.RESET.getCode()));
        }
    }

    /**
     * Método que procura um cliente na lista de clientes através do número de contribuinte.
     *
     * @param contribuinte Número de contribuinte do cliente a procurar.
     * @return Cliente encontrado ou null se não for encontrado.
     */
    public Cliente procurarClientePorContribuinte(int contribuinte) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getContribuinte() == contribuinte) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Método que solicita a introdução de um novo cliente ao utilizador e o acrescenta à lista de clientes.
     *
     */
    public void novoCliente() {
        Scanner scanner = new Scanner(System.in);

        String nome = (String)POOFS.receberInput(scanner, CategoriaInput.nome, "%s❯ Introduza o nome do cliente →%s ".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        if (nome == null) {
            System.out.println("%s● Operação de criação de novo cliente abortada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            return;
        }


        int contribuinte;
        contribuinte = (int)POOFS.receberInput(scanner, CategoriaInput.inteiro, "%s❯ Introduza o número de contribuinte →%s ".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        if (contribuinte == -1) {
            System.out.println("%s● Operação de criação de novo cliente abortada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            return;
        }

        String localizacao = (String)POOFS.receberInput(scanner, CategoriaInput.localizacao, "%s❯ Introduza a localização do cliente (Introduza 'Continente', 'Açores' ou 'Madeira') →%s ".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));

        adicionarCliente(nome, contribuinte, localizacao);
    }


    /**
     * Método que adiciona um novo cliente à lista de clientes.
     *
     * @param nome         Nome do cliente.
     * @param contribuinte Número de contribuinte do cliente.
     * @param localizacao  Localização do cliente.
     */
    public void adicionarCliente(String nome, int contribuinte, String localizacao) {
        Cliente novoCliente = new Cliente(nome, contribuinte, localizacao);
        listaClientes.add(novoCliente);
        System.out.println(Formatacao.YELLOW.getCode() + "Novo cliente '" + nome + "' adicionado com sucesso!" + Formatacao.RESET.getCode());
    }

    /**
     * Método que apresenta a lista de todos os clientes no sistema
     */
    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("%s● A lista de clientes está vazia!%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
        } else {
            System.out.println("%s───────────────────────────────────────────────────────────────────────────────────────────────%s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
            System.out.printf(" Lista de Clientes:\n");
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
            System.out.println("%s───────────────────────────────────────────────────────────────────────────────────────────────%s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        }
    }

    /**
     * Método que permite ao utilizador a edição de um cliente existente na lista de clientes.
     */
    public void editarCliente() {
        Scanner scanner = new Scanner(System.in);

        if (listaClientes.isEmpty()) {
            System.out.println("%s● A lista de clientes está vazia.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
        } else {
            int contribuinte;
            contribuinte = (int)POOFS.receberInput(scanner, CategoriaInput.inteiro, "%s❯ Introduza o número de contribuinte do cliente ao qual deseja alterar os dados: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
            if (contribuinte == -1) {
                System.out.println("%s● Operação de edição de cliente abortada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                return;
            }

            Cliente cliente = procurarClientePorContribuinte(contribuinte);
            if (cliente == null) {
                System.out.println("%s● Cliente não encontrado. Verifique os dados e tente novamente.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                return;
            }

            int opcao = -1;
            while (opcao != 0) {
                try {
                    System.out.println("─────────────────────────────────────");
                    System.out.println(" Que dados deseja alterar?");
                    System.out.println(" 1 - Nome");
                    System.out.println(" 2 - Contribuinte");
                    System.out.println(" 3 - Localização");
                    System.out.println(" 0 - Sair");
                    System.out.println("─────────────────────────────────────");

                    System.out.print("%s❯ Opção → %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                    opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao < 0 || opcao > 3) {
                        System.out.println("%s● Opção inválida! Introduza um número entre 0 e 3.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        continue; // Volta para a entrada de opção
                    }

                    switch (opcao) {
                        case 1 -> {
                            String novoNome;
                            while (true) {
                                System.out.print("%s❯ Novo nome: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                novoNome = scanner.nextLine().trim();
                                if (POOFS.isTextoValido(novoNome)) {
                                    cliente.setNome(novoNome);
                                    break;
                                } else {
                                    System.out.println("%sNome inválido. Apenas letras e espaços são permitidos.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                }
                            }
                        }
                        case 2 -> {
                            int novoContribuinte;
                            while (true) {
                                System.out.print("%s❯ Novo número de contribuinte: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                try {
                                    novoContribuinte = Integer.parseInt(scanner.nextLine());
                                    if (String.valueOf(novoContribuinte).length() != 9) {
                                        System.out.println("%s● Número de contribuinte inválido. O número deve ter 9 digitos!%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                    } else {
                                        Cliente novoClienteContribuinte = procurarClientePorContribuinte(novoContribuinte);
                                        if (novoClienteContribuinte == null) {
                                            cliente.setContribuinte(novoContribuinte);
                                            System.out.println("%sContribuinte alterado com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                                            break;
                                        } else {
                                            System.out.println("%s● Já existe um cliente com este número de contribuinte!%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("%s● Entrada inválida. Introduza um número inteiro.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                }
                            }
                        }
                        case 3 -> {
                            String novaLocalizacao;
                            System.out.print("%s❯ Introduza a nova localização do cliente ('Continente', 'Açores' ou 'Madeira'): %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                            while (true) {
                                try {
                                    novaLocalizacao = scanner.nextLine().trim();
                                    if (POOFS.isTextoValido(novaLocalizacao)) {
                                        if (POOFS.isLocalizacaoValida(novaLocalizacao)) {
                                            // Para a primeira letra de cada palavra ser maiúscula
                                            if (novaLocalizacao.equalsIgnoreCase("madeira")) {
                                                novaLocalizacao = "Madeira";
                                            } else if (novaLocalizacao.equalsIgnoreCase("açores")) {
                                                novaLocalizacao = "Açores";
                                            } else if (novaLocalizacao.equalsIgnoreCase("continente")) {
                                                novaLocalizacao = "Continente";
                                            }
                                            cliente.setLocalizacao(novaLocalizacao);
                                            break;
                                        } else {
                                            System.out.print("%s● Não existe essa localização! Introduza 'Continente', 'Açores' ou 'Madeira': %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                        }
                                    } else {
                                        System.out.println("%s● Localização inválida. Apenas letras e espaços são permitidos.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                    }
                                } catch (Exception e) {
                                    System.out.println("%s● Erro ao processar a localização: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                                }
                            }
                        }
                        case 0 -> System.out.println("%s● Alterações terminadas.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        default -> System.out.println("%s● Opção inválida. Tente novamente.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("%s● Erro ao processar a opção: Introduza um número válido.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("%s● Erro inesperado: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                }
                System.out.print("%s❯ Deseja alterar mais alguma informação? (S/N): %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                String continuar = scanner.nextLine();
                while (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                    System.out.println("%s● Opção inválida. Por favor, introduza S/N.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    continuar = scanner.nextLine();
                }
                if (continuar.equalsIgnoreCase("N")) {
                    System.out.println("%s● Alterações guardadas: edição de cliente terminada%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                    break;
                }
            }
        }
    }
}