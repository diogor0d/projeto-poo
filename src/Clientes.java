//dúvida: linha 52 e 155; 104

import java.util.ArrayList;
import java.util.Scanner;

public class Clientes{
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
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!(Character.isLetter(c) || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    // Metodo para ler os dados e criar o cliente
    public void novoCliente() {
        Scanner scanner = new Scanner(System.in);

        String nome;
        while(true){
            System.out.print("Digite o nome do cliente: ");
            nome = scanner.nextLine();
            if (isTextoValido(nome)) {
                break;
            } else{
                System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
            }
        }
        int contribuinte;
        while(true){
            System.out.print("Digite o número de contribuinte: ");
            //duvida: o input pode ter 123 dasd dsad; e ele fica só com o 123, mas deveríamos mandar repetir
            if (scanner.hasNextInt()){
                contribuinte = scanner.nextInt();
                scanner.nextLine();
                break;
            }else{
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next();
            }
        }
        String localizacao;
        while(true){
            System.out.print("Digite a localização do cliente: ");
            localizacao = scanner.nextLine();
            if(isTextoValido(localizacao)){
                break;
            } else{
                System.out.println("Localização inválida. Apenas letras e espaços são permitidos.");
            }
        }
        adicionarCliente(nome, contribuinte, localizacao);
    }

    // Metodo para adicionar um novo cliente à lista
    public void adicionarCliente(String nome, int contribuinte, String localizacao) {
        Cliente novoCliente = new Cliente(nome, contribuinte, localizacao);
        listaClientes.add(novoCliente);
        System.out.println("Novo cliente, " +nome+", adicionado com sucesso!");
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

    // Metodo para procurar um cliente na lista a partir do seu numero de contribuinte
    public Cliente procurarClientePorContribuinte(int contribuinte) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getContribuinte() == contribuinte) {
                return cliente;
            }
        }
        return null;
    }

    //duvida: deveriamos usar o contribuinte em vez de nome??
    // Metodo para editar o(s) dado(s) dum cliente
    public void editarCliente() {
        Scanner scanner = new Scanner(System.in);
        if (listaClientes.isEmpty()) {
            System.out.println("A lista de clientes está vazia.");
        }
        Cliente cliente = null;
        while (cliente == null) {
            System.out.print("Digite o nome do cliente ao qual quer alterar os dados: ");
            String nome = scanner.nextLine();
            if (isTextoValido(nome)) {
                for (Cliente clientes : listaClientes) {
                    if (clientes.getNome().equalsIgnoreCase(nome)) {
                        cliente = clientes;
                        System.out.println("Cliente " + nome + " encontrado.");
                        break;
                    }
                }
                if (cliente == null) {
                    System.out.println("Cliente não encontrado! Tente novamente.");
                }
            } else {
                System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
            }
        }
        int opcao = -1;

        while (opcao != 0) {
            System.out.print("Que dados deseja alterar?\n1- Nome\n2- Contribuinte\n3- Localização\n0- Cancelar\nOpção-> ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    String novoNome;
                    while(true){
                        System.out.print("Novo nome: ");
                        novoNome = scanner.nextLine();
                        if(isTextoValido(novoNome)) {
                            cliente.setNome(novoNome);
                            System.out.println("Nome alterado com sucesso.");
                            break;
                        } else{
                            System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
                        }
                    }
                    break;
                case 2:
                    int novoContribuinte;
                    while(true) {
                        System.out.print("Novo número de contribuinte: ");
                        //duvida: o input pode ter 123 dasd dsad; e ele fica só com o 123, mas deveríamos mandar repetir
                        if (scanner.hasNextInt()) {
                            novoContribuinte = scanner.nextInt();
                            scanner.nextLine();
                            cliente.setContribuinte(novoContribuinte);
                            System.out.println("Contribuinte alterado com sucesso.");
                            break;
                        } else {
                            System.out.println("Entrada inválida. Digite um número inteiro.");
                            scanner.next();
                        }
                    }
                    break;
                case 3:
                    String novaLocalizacao;
                    while(true){
                        System.out.print("Nova localização: ");
                        novaLocalizacao = scanner.nextLine();
                        if(isTextoValido(novaLocalizacao)){
                            cliente.setLocalizacao(novaLocalizacao);
                            System.out.println("Localização alterada com sucesso.");
                            break;
                        }else{
                            System.out.println("Nome inválido. Apenas letras e espaços são permitidos.");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Alteração cancelada.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
            }
            System.out.print("Deseja alterar mais algum dado? (S ou N): ");
            String continuar = scanner.nextLine();
            if (continuar.equalsIgnoreCase("N")) {
                break;
            }
        }
    }
}
