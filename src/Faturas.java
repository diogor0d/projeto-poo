//vizualizarFatura e editarFatura estão incompletos

import java.util.ArrayList;
import java.util.Scanner;

public class Faturas {
    private ArrayList<Fatura> listaFaturas;
    private final Clientes clientes;

    // Construtor da classe Faturas
    public Faturas(Clientes clientes) {
        this.listaFaturas = new ArrayList<>();
        this.clientes = clientes;
    }

    // Metodo para tornar uma lista de faturas na lista de faturas
    public void setListaFaturas(ArrayList<Fatura> novaListaFaturas) {
        try {
            if (novaListaFaturas != null) {
                this.listaFaturas = novaListaFaturas;
                System.out.println("Lista de faturas atualizada com sucesso.");
            } else {
                System.out.println("A nova lista de faturas é inválida (null).");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    // Metodo para verificar se uma Data é válida ou não
    private boolean isDataValida(int dia, int mes, int ano) {
        // Verifica se o mês está no intervalo correto
        if (ano > 2024) return false;

        if (mes < 1 || mes > 12) return false;

        // Dias em cada mês
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Verifica anos bissextos
        if (mes == 2 && ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0))) {
            diasPorMes[1] = 29;
        }

        // Verifica se o dia está no intervalo correto
        return dia > 0 && dia <= diasPorMes[mes - 1];
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

    // Metodo para ler os dados e criar a fatura
    public void novaFatura() {
        Scanner scanner = new Scanner(System.in);
        int numero;
        while (true) {
            try {
                System.out.print("Digite o número da fatura: ");
                numero = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
        int contribuinte;
        Cliente cliente;
        while (true) {
            try {
                System.out.print("Digite o número de contribuinte: ");
                contribuinte = Integer.parseInt(scanner.nextLine());
                cliente = clientes.procurarClientePorContribuinte(contribuinte);

                if (cliente != null) {
                    break;
                } else {
                    System.out.println("Cliente não encontrado. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }

        Data data;
        while (true) {
            try {
                System.out.print("Digite a data (dd/mm/aaaa): ");
                String dataStr = scanner.nextLine();

                String[] partes = dataStr.split("/");
                if (partes.length != 3) {
                    throw new IllegalArgumentException("Formato inválido. Use dd/mm/aaaa.");
                }

                int dia = Integer.parseInt(partes[0].trim());
                int mes = Integer.parseInt(partes[1].trim());
                int ano = Integer.parseInt(partes[2].trim());

                if (isDataValida(dia, mes, ano)) {
                    data = new Data(dia, mes, ano);
                    break;
                } else {
                    System.out.println("Data incorreta. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar a data. Certifique-se de usar apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Digite os produtos a adicionar (codigo, nome, descricao, quantidade, preco): ");
        System.out.println("Digite 'fim' para encerrar.");
        System.out.println("Cada produto deve estar em uma linha.");

        ArrayList<Produto> produtos = new ArrayList<>();
        int codigo;
        String nome;
        String descricao;
        int quantidade;
        double preco;

        while (true) {
            try {
                System.out.print("Produto: ");
                String linha = scanner.nextLine();
                if (linha.equalsIgnoreCase("fim")) {
                    break;
                }
                String[] atributos = linha.split(",");
                if (atributos.length != 5) {
                    System.out.println("Erro: Linha com formato inválido. Esperado: codigo, nome, descricao, quantidade, preco");
                    continue;
                }
                codigo = Integer.parseInt(atributos[0].trim());
                nome = atributos[1].trim();
                descricao = atributos[2].trim();
                quantidade = Integer.parseInt(atributos[3].trim());
                preco = Double.parseDouble(atributos[4].trim());

                if (nome.isEmpty() || descricao.isEmpty() || !(isTextoValido(nome)) || !(isTextoValido(descricao))) {
                    //nao sei que print dar ahaahha
                    System.out.println("Erro: Linha com formato inválido. Esperado: codigo, nome, descricao, quantidade, preco");
                    continue;
                }

                Produto produto = new Produto(codigo, nome, descricao, quantidade, preco);
                produtos.add(produto);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar produto. Verifique os valores numéricos.");
            } catch (Exception e) {
                System.out.println("Erro inesperado ao processar produto: " + e.getMessage());
            }
        }

        // Criação da fatura
        try {
            adicionarFatura(numero, cliente, data, produtos);
            System.out.println("Fatura criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar fatura: " + e.getMessage());
        }
    }


    // Metodo para adicionar uma nova fatura à lista
    public void adicionarFatura(int numero, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        Fatura novaFatura = new Fatura(numero, cliente, data, produtos);
        listaFaturas.add(novaFatura);
        System.out.println("Nova fatura, " + numero + ", adicionada com sucesso!");
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
    public void editarFatura() {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!listaFaturas.isEmpty()) {
                boolean faturaEncontrada = false;
                while (!faturaEncontrada) {
                    System.out.print("Qual é o número da fatura, à qual quer alterar os dados? ");
                    int num = Integer.parseInt(scanner.nextLine());
                    for (Fatura fatura : listaFaturas) {
                        if (fatura.getNum() == num) {
                            System.out.print("Fatura " + num + " encontrada.");
                            faturaEncontrada = true;
                            int opcao = -1;
                            while (opcao != 0) {
                                System.out.print("\nQue dados deseja alterar?\n1- Número\n2- Cliente\n3- Data\n4- Produto(s)\n0- Cancelar\nOpção-> ");
                                try {
                                    opcao = Integer.parseInt(scanner.nextLine());
                                    if (opcao < 0 || opcao > 4) {
                                        System.out.println("Opção inválida! Por favor, digite um número entre 0 e 4.");
                                        continue;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada inválida! Por favor, digite um número válido.");
                                    continue;
                                }

                                switch (opcao) {
                                    case 1:
                                        try {
                                            System.out.print("Novo número da fatura: ");
                                            int novoNumero = Integer.parseInt(scanner.nextLine());
                                            fatura.setNumero(novoNumero);
                                            System.out.println("Número da fatura alterado com sucesso.");
                                        } catch (Exception e) {
                                            System.out.println("Erro ao alterar o número da fatura: " + e.getMessage());
                                        }
                                        break;
                                    case 2:
                                        try {
                                            System.out.print("Número de contribuinte do novo cliente: ");
                                            int novoContribuinte = Integer.parseInt(scanner.nextLine());
                                            Cliente novoCliente = clientes.procurarClientePorContribuinte(novoContribuinte);
                                            if (novoCliente != null) {
                                                fatura.setCliente(novoCliente);
                                                System.out.println("Cliente alterado com sucesso.");
                                            } else {
                                                System.out.println("Cliente não encontrado.");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Erro ao alterar cliente: " + e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        try {
                                            System.out.print("Nova data (dd/mm/aaaa): ");
                                            String dataStr = scanner.nextLine();
                                            String[] partes = dataStr.split("/");
                                            if (partes.length == 3) {
                                                int dia = Integer.parseInt(partes[0]);
                                                int mes = Integer.parseInt(partes[1]);
                                                int ano = Integer.parseInt(partes[2]);
                                                Data novaData = new Data(dia, mes, ano);
                                                fatura.setData(novaData);
                                                System.out.println("Data alterada com sucesso.");
                                            } else {
                                                System.out.println("Formato de data inválido.");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Erro ao alterar a data: " + e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        //editar produtos ainda não implementada

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
                    }
                    if (!faturaEncontrada) {
                        System.out.println("Fatura não encontrada. Tente novamente.");
                    }
                }
            } else {
                System.out.println("A lista de faturas está vazia.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
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
