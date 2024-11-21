import java.util.ArrayList;
import java.util.Scanner;

public class Clientes {
    private ArrayList<Cliente> listaClientes;

    // Construtor da classe Clientes
    public Clientes() {
        this.listaClientes = new ArrayList<>();
    }

    // Metodo para tornar uma lista de clientes na lista de clientes
    public void setListaClientes(ArrayList<Cliente> novaListaClientes) {
        if (novaListaClientes != null) {
            this.listaClientes = novaListaClientes;
            System.out.println("Lista de clientes atualizada com sucesso.");
        } else {
            System.out.println("A nova lista de clientes é inválida (null).");
        }
    }

    // Metodo para determinar se uma string é constituida apenas por caracteres e espaços
    public boolean isTextoValido(String texto) {
        if(!texto.isEmpty()){
            texto = String.join(" ", texto.split("\\s+"));
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                if (!(Character.isLetter(c) || c == ' ')) {
                    return false;
                }
            }
        } else{
            return false;
        }
        return true;
    }

    public boolean isLocalizacaoValida(String localizacao){
        ArrayList<String> localizacoes = new ArrayList<>();
        localizacoes.add("portugal continental");
        localizacoes.add("açores");
        localizacoes.add("madeira");
        return localizacoes.contains(localizacao);
    }

    // Metodo para procurar um cliente na lista a partir do seu numero de contribuinte
    public Cliente procurarClientePorContribuinte(int contribuinte) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getContribuinte() == contribuinte) {
                return cliente;
            }
        }
        return null;
    }

    // Metodo para procurar um cliente na lista a partir do seu nome
    public Cliente procurarClientePorNome(String nome) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    // Metodo para ler os dados e criar o cliente
    public void novoCliente() {
        Scanner scanner = new Scanner(System.in);

        String nome;
        while (true) {
            try {
                System.out.print("Digite o nome do cliente: ");
                nome = scanner.nextLine();
                nome = nome.trim();
                if (isTextoValido(nome)) {
                    Cliente clienteComEsteNome = procurarClientePorNome(nome);
                    if(clienteComEsteNome != null){
                        System.out.println("Já existe um cliente com este nome!");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar o nome: " + e.getMessage());
            }
        }

        int contribuinte;
        while (true) {
            try {
                System.out.print("Digite o número de contribuinte: ");
                contribuinte = Integer.parseInt(scanner.nextLine());
                Cliente clienteComEsteContribuinte = procurarClientePorContribuinte(contribuinte);
                if (clienteComEsteContribuinte != null) {
                    System.out.println("Já existe um cliente com este número de contribuinte!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            } catch (Exception e) {
                System.out.println("Erro ao processar o número de contribuinte: " + e.getMessage());
            }
        }

        String localizacao;
        while (true) {
            try {
                System.out.print("Digite a localização do cliente (Digite 'Portugal Continental', 'Açores' ou 'Madeira'): ");
                localizacao = scanner.nextLine();
                localizacao = localizacao.trim();
                if (isTextoValido(localizacao)) {
                    if(isLocalizacaoValida(localizacao)){
                        break;
                    } else{
                        System.out.print("Não existe essa localização! Digite 'Portugal Continental', 'Açores' ou 'Madeira'");
                    }
                } else {
                    System.out.println("Localização inválida. Apenas letras e espaços são permitidos.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao processar a localização: " + e.getMessage());
            }
        }
        adicionarCliente(nome, contribuinte, localizacao);
    }


    // Metodo para adicionar um novo cliente à lista
    public void adicionarCliente(String nome, int contribuinte, String localizacao) {
        Cliente novoCliente = new Cliente(nome, contribuinte, localizacao);
        listaClientes.add(novoCliente);
        System.out.println("Novo cliente, " + nome + ", adicionado com sucesso!");
    }

    // Metodo para listar os clientes
    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("A lista de clientes está vazia!");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        }
    }


    //duvida: deveriamos usar o contribuinte em vez de nome??
    public void editarCliente() {
        Scanner scanner = new Scanner(System.in);

        if (listaClientes.isEmpty()) {
            System.out.println("A lista de clientes está vazia.");
        } else {
            Cliente cliente;
            int contribuinte;
            while (true) {
                try {
                    System.out.print("Digite o número de contribuintedo cliente ao qual deseja alterar os dados: ");
                    contribuinte = Integer.parseInt(scanner.nextLine());
                    cliente = procurarClientePorContribuinte(contribuinte);
                    if (cliente != null) {
                        System.out.println("Cliente " + cliente.getNome() + " encontrado!");
                        break;
                    } else {
                        System.out.println("Cliente não encontrado! Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número inteiro.");
                } catch (Exception e) {
                    System.out.println("Erro ao processar o número de contribuinte: " + e.getMessage());
                }
            }

            int opcao = -1;
            while (opcao != 0) {
                try {
                    System.out.print("Que dados deseja alterar?\n1- Nome\n2- Contribuinte\n3- Localização\n0- Cancelar\nOpção-> ");
                    opcao = Integer.parseInt(scanner.nextLine());
                    if (opcao < 0 || opcao > 3) {
                        System.out.println("Opção inválida! Digite um número entre 0 e 3.");
                        continue; // Volta para a entrada de opção
                    }

                    switch (opcao) {
                        case 1:
                            String novoNome;
                            while (true) {
                                System.out.print("Novo nome: ");
                                novoNome = scanner.nextLine();
                                novoNome = novoNome.trim();
                                if (isTextoValido(novoNome)) {
                                    Cliente novoClienteNome = procurarClientePorNome(novoNome);
                                    if(novoClienteNome == null){
                                        cliente.setNome(novoNome);
                                        System.out.println("Nome alterado com sucesso.");
                                        break;
                                    } else {
                                        System.out.println("Já existe um cliente com este nome!");
                                    }
                                } else {
                                    System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
                                }
                            }
                            break;

                        case 2:
                            int novoContribuinte;
                            while (true) {
                                System.out.print("Novo número de contribuinte: ");
                                try {
                                    novoContribuinte = Integer.parseInt(scanner.nextLine());
                                    Cliente novoClienteContribuinte = procurarClientePorContribuinte(novoContribuinte);
                                    if(novoClienteContribuinte == null){
                                        cliente.setContribuinte(novoContribuinte);
                                        System.out.println("Contribuinte alterado com sucesso.");
                                        break;
                                    } else {
                                        System.out.println("Já existe um cliente com este número de contribuinte!");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida. Digite um número inteiro.");
                                }
                            }
                            break;

                        case 3:
                            String novaLocalizacao;
                            while (true) {
                                System.out.print("Nova localização (Digite 'Portugal Continental', 'Açores' ou 'Madeira'): ");
                                novaLocalizacao = scanner.nextLine();
                                novaLocalizacao = novaLocalizacao.trim();
                                if (isTextoValido(novaLocalizacao)) {
                                    if (isLocalizacaoValida(novaLocalizacao)) {
                                        cliente.setLocalizacao(novaLocalizacao);
                                        System.out.println("Localização alterada com sucesso.");
                                        break;
                                    } else{
                                        System.out.print("Não existe essa localização! Digite 'Portugal Continental', 'Açores' ou 'Madeira'");
                                    }
                                } else {
                                    System.out.println("Localização inválida. Apenas letras e espaços são permitidos.");
                                }
                            }
                            break;

                        case 0:
                            System.out.println("Alteração cancelada.");
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao processar a opção: Digite um número válido.");
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
                System.out.print("Deseja alterar mais algum dado? (S ou N): ");
                String continuar = scanner.nextLine();
                while (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                    System.out.println("Opção inválida. Por favor, digite 'S' para sim ou 'N' para não.");
                    continuar = scanner.nextLine();
                }
                if (continuar.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
    }
}