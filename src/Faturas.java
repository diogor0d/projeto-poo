//falta adicionar proteçoes
//vizualizarFatura e editarFatura estão incompletos
//duvida devemos meter a linha 10 a private?

import java.util.ArrayList;
import java.util.Scanner;

public class Faturas{
    private ArrayList<Fatura> listaFaturas;
    private Clientes clientes;

    // Construtor da classe Faturas
    public Faturas(Clientes clientes) {
        this.listaFaturas = new ArrayList<>();
        this.clientes = clientes;
    }

    // Metodo para tornar uma lista de faturas na lista de faturas
    public void setListaFaturas(ArrayList<Fatura> novaListaFaturas) {
        if (novaListaFaturas != null) {
            this.listaFaturas = novaListaFaturas;
            System.out.println("Lista de faturas atualizada com sucesso.");
        } else {
            System.out.println("A nova lista de faturas é inválida (null).");
        }
    }

    // Metodo para ler os dados e criar a fatura
    public void novaFatura() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número da fatura: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n" pendente

        System.out.print("Digite o contriubuinte do cliente: ");
        int contribuinte = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clientes.procurarClientePorContribuinte(contribuinte);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite a data (dd/mm/aaaa): ");
        String dataStr = scanner.nextLine();

        // Converter a string de data para o formato Data
        String[] partes = dataStr.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        Data data = new Data(dia, mes, ano);

        System.out.print("Digite os produtos a adicionar (codigo, nome, descricao, quantidade, preco): ");
        System.out.println("Digite 'fim' para encerrar.");
        System.out.println("Cada produto deve estar em uma linha.");

        ArrayList<Produto> produtos = new ArrayList<>();

        while (true) {
            System.out.print("Produto: ");
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("fim")) break;

            String[] atributos = linha.split(",");
            if (atributos.length == 5) { // Verifica se há 5 campos
                try {
                    int codigo = Integer.parseInt(atributos[0].trim());
                    String nome = atributos[1].trim();
                    String descricao = atributos[2].trim();
                    int quantidade = Integer.parseInt(atributos[3].trim());
                    double preco = Double.parseDouble(atributos[4].trim());

                    Produto produto = new Produto(codigo, nome, descricao, quantidade, preco);
                    produtos.add(produto);
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao processar produto: " + linha);
                }
            } else {
                System.out.println("Erro: Linha com formato inválido. Esperado: codigo, nome, descricao, quantidade, preco");
            }
        }
        adicionarFatura(numero, cliente, data, produtos);
    }


    // Metodo para adicionar uma nova fatura à lista
    public void adicionarFatura(int numero, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        Fatura novaFatura= new Fatura(numero, cliente, data, produtos);
        listaFaturas.add(novaFatura);
        System.out.println("Nova fatura, " +numero+", adicionada com sucesso!");
    }

    // Metodo para listar as faturas
    public void listarFaturas() {
        if (listaFaturas.isEmpty()) {
            System.out.println("Nenhuma fatura cadastrada.");
        } else {
            System.out.println("Lista de Faturas:");
            for (Fatura fatura : listaFaturas) {
                System.out.println(fatura);
            }
        }
    }

    // Metodo para editar o(s) dado(s) duma fatura
    public void editarFatura(){
        Scanner scanner = new Scanner(System.in);
        if (!listaFaturas.isEmpty()) {
            System.out.print("Qual é o número da fatura, à qual quer alterar os dados? ");
            int num = scanner.nextInt();
            scanner.nextLine();
            for (Fatura fatura : listaFaturas) {
                if (fatura.getNum() == num) {
                    System.out.print("Fatura " + num + " encontrada.");
                    int opcao = -1;
                    while (opcao != 0) {
                        System.out.print("\nQue dados deseja alterar?\n1- Número\n2- Cliente\n3- Data\n4- Produto(s)\n0- Cancelar\nOpção-> ");
                        opcao = scanner.nextInt();
                        scanner.nextLine();  // Limpar o buffer do scanner
                        switch (opcao) {
                            case 1:
                                System.out.print("Novo número da fatura: ");
                                int novoNumero = scanner.nextInt();
                                scanner.nextLine();  // Limpar o buffer
                                fatura.setNumero(novoNumero);
                                System.out.println("Número da fatura alterado com sucesso.");
                                break;
                            case 2:
                                System.out.print("Número de contribuinte do novo cliente: ");
                                int novoContribuinte = scanner.nextInt();
                                scanner.nextLine();
                                Cliente novoCliente = clientes.procurarClientePorContribuinte(novoContribuinte);
                                if(novoCliente != null){
                                    fatura.setCliente(novoCliente);
                                    System.out.println("Cliente alterado com sucesso.");
                                }else{
                                    System.out.println("Cliente não encontrado.");
                                    return;
                                }
                            case 3:
                                System.out.print("Nova data: ");
                                String dataStr = scanner.nextLine();
                                String[] partes = dataStr.split("/");
                                int dia = Integer.parseInt(partes[0]);
                                int mes = Integer.parseInt(partes[1]);
                                int ano = Integer.parseInt(partes[2]);
                                Data novaData = new Data(dia, mes, ano);
                                fatura.setData(novaData);
                                System.out.println("Data alterada com sucesso.");
                                break;
                            case 4:
                                int opcao_faturas= -1;
                                while (opcao_faturas != 0) {
                                    System.out.print("\nDeseja:\n1- Alterar um produto\n2- Adicionar um novo produto\n3- Eliminar um produto\n0- Cancelar\nOpção-> ");
                                    int nova_opcao = scanner.nextInt();
                                    scanner.nextLine();
                                    switch(nova_opcao){
                                        //adicionar as cenas
                                }
                                }
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
                System.out.println("Fatura não encontrada.");
            }
        }
        else {
            System.out.println("A lista de faturas está vazia.");
        }
    }


    public void vizualizarFatura() {
        Scanner scanner = new Scanner(System.in);
        if (!listaFaturas.isEmpty()) {
            System.out.print("Qual é o número da fatura que quer vizualizar? ");
            int num = scanner.nextInt();
            scanner.nextLine();
            for (Fatura fatura : listaFaturas) {
                if (fatura.getNum() == num) {
                    System.out.print("Fatura " + num + " encontrada.");
                    System.out.print("Número: " + fatura.getNum());
                    Cliente cliente = fatura.getCliente();
                    System.out.print(cliente);
                    //falta lista de produtos e preços com e sem IVA etc...
                }
            }
        } else {
            System.out.println("A lista de faturas está vazia");
        }
    }
}
