//vizualizarFatura e editarFatura estão incompletos

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Faturas {
    private ArrayList<Fatura> listaFaturas;
    private final Clientes clientes;
    private final Produtos produtos;

    // Construtor da classe Faturas
    public Faturas(Clientes clientes, Produtos produtos) {
        this.listaFaturas = new ArrayList<>();
        this.clientes = clientes;
        this.produtos = produtos; // Passar a mesma instância de Produtos
    }

    public ArrayList<Fatura> getListaFaturas() {
        return listaFaturas;
    }


    // Metodo para tornar uma lista de faturas na lista de faturas
    public void setListaFaturas(ArrayList<Fatura> novaListaFaturas) {
        try {
            if (novaListaFaturas != null) {
                this.listaFaturas = novaListaFaturas;
                System.out.println("%sLista de Faturas atualizada.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
            } else {
                System.out.println("%s● A nova lista de Faturas é inválida (null).%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            }
        } catch (Exception e) {
            System.out.println("%s● Ocorreu um erro inesperado: %s%s".formatted(Formatacao.RED.getCode(),e.getMessage(), Formatacao.RESET.getCode()));
        }
    }


    // Metodo para ler os dados e criar a fatura
    public void novaFatura() {
        Scanner scanner = new Scanner(System.in);

        int numero;
        while (true) {
            System.out.printf("%s❯ Deseja introduzir um número especifico para a fatura? (S ou N): %s", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
            String inserirNumManual = scanner.nextLine();
            if (inserirNumManual.equalsIgnoreCase("N")) {
                // Gerar e atribuir um número para a fatura de forma automaticac
                numero = gerarNumFatura();
                System.out.printf("%s● Foi atribuído o seguinte número à sua fatura: %d%s\n", Formatacao.GREEN.getCode(), numero, Formatacao.RESET.getCode());
                break;
            } else if (inserirNumManual.equalsIgnoreCase("S")) {
                // Permitir ao utilizador atribuir um número para a fatura
                while (true) {
                    try {
                        System.out.printf("%s❯ Introduza o número da fatura: %s", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
                        numero = Integer.parseInt(scanner.nextLine());
                        Fatura faturaEncontrada = procurarFatura(numero);
                        if (faturaEncontrada == null) {
                            break;
                        } else {
                            System.out.printf("%s● Já existe uma fatura com esse número!\n%s", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                        }
                    } catch (NumberFormatException e) {
                        System.out.printf("%s● Entrada inválida. Introduza um número inteiro.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    }
                }
                break;
            } else {
                System.out.printf("%s● Entrada inválida. Introduza apenas 'S' ou 'N'.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            }
        }



        Cliente cliente;
        int contribuinte = (int) POOFS.receberInput(scanner, CategoriaInput.inteiro, "%s❯ Introduza o número de contribuinte: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        if (contribuinte == -1) {
            System.out.printf("%sCriação de fatura cancelada.%s\n", Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
            return;
        } else {
            cliente = clientes.procurarClientePorContribuinte(contribuinte);
            if (cliente == null) {
                while (cliente == null) {
                    System.out.printf("%s● Cliente não encontrado. Introduza -1 para cancelar.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    contribuinte = (int) POOFS.receberInput(scanner, CategoriaInput.inteiro, "Introduza o número de contribuinte: ");
                    if (contribuinte == -1) {
                        System.out.printf("%s● Criação de fatura cancelada.%s\n", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
                        return;
                    }
                    cliente = clientes.procurarClientePorContribuinte(contribuinte);
                }
            }
        }

        Data data = (Data) POOFS.receberInput(scanner, CategoriaInput.data, "%s❯ Introduza a data da fatura (DD/MM/AAAA): %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        if (data == null) {
            System.out.printf("%s● Criação de fatura cancelada.%s\n", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
            return;
        }

        produtos.listarProdutos(produtos.getListaProdutos());
        System.out.printf("%s❯ Insira os códigos dos produtos a adicionar (separados por ','):%s\n".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));

        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String line = scanner.nextLine();
        String[] partes = line.split(",");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }
        if (partes[0].equals(-1)) {
            System.out.println("%s● Criação de fatura cancelada.%s\n".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
            return;
        }
        try {
            for (String codigo : partes) {
                Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(codigo));
                if (produto != null) {
                    System.out.println("%s● Produto %s adicionado.%s".formatted(Formatacao.GREEN.getCode(),produto.getNome(), Formatacao.RESET.getCode()));
                    listaProdutos.add(produto);
                } else {
                    System.out.println("%s● Produto com o código %s não encontrado. Verifique o código e tente adiciona-lo novamente mais tarde.".formatted(Formatacao.RED.getCode(),codigo,Formatacao.RESET.getCode()));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("%s● Erro ao processar os produtos: %s%s".formatted(Formatacao.RED.getCode(), line, Formatacao.RESET.getCode()));
        }

        if (listaProdutos.isEmpty()) {
            System.out.println("%s● Nenhum produto registado na fatura. Criação de fatura abortada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            return;
        }


        try {
            adicionarFatura(numero, cliente, data, listaProdutos);
            System.out.println("%s● Fatura criada com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
        } catch (Exception e) {
            System.out.println("%s● Erro ao criar fatura: %s&s".formatted(Formatacao.RED.getCode(),e.getMessage(),Formatacao.RESET.getCode()));
        }
    }


    // Metodo para adicionar uma nova fatura à lista
    public void adicionarFatura(int numero, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        Fatura novaFatura = new Fatura(numero, cliente, data, produtos);
        listaFaturas.add(novaFatura);
        System.out.println(Formatacao.YELLOW.getCode() + "Nova fatura " + numero + " adicionada com sucesso!" + Formatacao.RESET.getCode());
    }

    // Metodo para listar as faturas
    public void listarFaturas() {
        if (listaFaturas.isEmpty()) {
            System.out.println("Nenhuma fatura declarada.");
        } else {
            int largura = 183; // Width of the separator line
            String titulo = "Visão Geral Faturas:";
            int enquadramento = (largura - titulo.length()) / 2;

            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.printf("%" + enquadramento + "s%s%" + (enquadramento - 1) + "s┃\n", "", titulo, "");
            for (Fatura fatura : listaFaturas) {

                System.out.printf(" %-179s \n", fatura.toString());
            }
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }

    // Metodo para editar o(s) dado(s) duma fatura
    //falta mudar os produtos!!
    public void editarFatura() {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!listaFaturas.isEmpty()) {
                Fatura faturaEncontrada;
                while (true) {
                    System.out.print("%s❯ Qual é o número da fatura a ser alterada? %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                    int num = Integer.parseInt(scanner.nextLine());
                    faturaEncontrada = procurarFatura(num);
                    if (faturaEncontrada != null) {
                        System.out.println("%sFatura %s encontrada.%s".formatted(Formatacao.GREEN.getCode(), num,Formatacao.RESET.getCode()));
                        break;
                    } else {
                        System.out.println("%s● Não existe nenhuma fatura com esse número!%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    }
                }
                int opcao = -1;
                while (opcao != 0) {
                    System.out.println("───────────────────────────────────");
                    System.out.print(" Que informações deseja alterar?\n");
                    System.out.print("  1 - Número                 \n");
                    System.out.print("  2 - Contribuinte              \n");
                    System.out.print("  3 - Data                 \n");
                    System.out.print("  4 - Produto(s)           \n");
                    System.out.print("  0 - Sair             \n");
                    System.out.println("───────────────────────────────────");
                    System.out.print("%s❯ Opção → %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                    try {
                        opcao = Integer.parseInt(scanner.nextLine());
                        if (opcao < 0 || opcao > 4) {
                            System.out.println("%s● Opção inválida! Por favor, introduza um número entre 0 e 4.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("%s● Entrada inválida! Por favor, introduza um número válido.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        continue;
                    }

                    switch (opcao) {
                        case 1 -> {
                            try {
                                System.out.print("%s❯ Especifique o novo número da fatura: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                int novoNumero = Integer.parseInt(scanner.nextLine());
                                faturaEncontrada.setNumero(novoNumero);
                                System.out.println("%s● Número da fatura alterado com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                            } catch (Exception e) {
                                System.out.println("%s● Erro ao alterar o número da fatura: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                            }
                        }
                        case 2 -> {
                            try {
                                System.out.print("%s❯ Número de contribuinte do novo cliente: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                int novoContribuinte = Integer.parseInt(scanner.nextLine());
                                Cliente novoCliente = clientes.procurarClientePorContribuinte(novoContribuinte);
                                if (novoCliente != null) {
                                    faturaEncontrada.setCliente(novoCliente);
                                    System.out.println("%sCliente alterado com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                                } else {
                                    System.out.println("%s● Cliente não encontrado.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                }
                            } catch (Exception e) {
                                System.out.println("%s● Erro ao alterar cliente: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                            }
                        }
                        case 3 -> {
                            try {
                                System.out.print("%s❯ Nova data (dd/mm/aaaa): %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                String dataStr = scanner.nextLine();
                                String[] partes = dataStr.split("/");
                                if (partes.length == 3) {
                                    int dia = Integer.parseInt(partes[0]);
                                    int mes = Integer.parseInt(partes[1]);
                                    int ano = Integer.parseInt(partes[2]);
                                    Data novaData = new Data(dia, mes, ano);
                                    faturaEncontrada.setData(novaData);
                                    System.out.println("%sData alterada com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                                } else {
                                    System.out.println("%s● Formato de data inválido.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                }
                            } catch (Exception e) {
                                System.out.println("%s● Erro ao alterar a data: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                            }
                        }
                        case 4 -> {
                            try {
                                ArrayList<Produto> produtosDaFatura = faturaEncontrada.getProdutos();
                                if (produtosDaFatura == null || produtosDaFatura.isEmpty()) {
                                    System.out.println("%s● Não há produtos associados a esta fatura.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                }
                                int opcaoProdutos = -1;
                                while (opcaoProdutos != 0) {
                                    produtos.listarProdutos(produtosDaFatura);
                                    System.out.println("────────────────────────────────────────────");
                                    System.out.print(" Deseja:                      \n");
                                    System.out.print("  1- Adicionar um produto     \n");
                                    System.out.print("  2- Remover um produto       \n");
                                    System.out.print("  0- Sair                 \n");
                                    System.out.println("────────────────────────────────────────────");
                                    System.out.print("%s❯ Opção → %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));

                                    try {
                                        opcaoProdutos = Integer.parseInt(scanner.nextLine());
                                        if (opcaoProdutos < 0 || opcaoProdutos > 2) {
                                            System.out.println("%s● Opção inválida! Por favor, introduza um número entre 0 e 2.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                            continue;
                                        }
                                        switch (opcaoProdutos) {
                                            case 1 -> {
                                                produtos.listarProdutos(produtos.getListaProdutos());
                                                System.out.print("%s❯ Insira o código do produto que deseja adicionar: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                                String codigoProdutoAdicionar = scanner.nextLine().trim();
                                                Produto produtoAdicionar = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProdutoAdicionar));
                                                if (produtoAdicionar != null) {
                                                    produtosDaFatura.add(produtoAdicionar);
                                                    System.out.println("%sProduto adicionado com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                                                }
                                            }
                                            case 2 -> {
                                                System.out.print("%s❯ Insira o codigo do produto a remover: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                                                String codigoProdutoRemover = scanner.nextLine().trim();
                                                Produto produtoRemover = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProdutoRemover));
                                                if (produtoRemover != null) {
                                                    if (produtosDaFatura.contains(produtoRemover)) {
                                                        produtosDaFatura.remove(produtoRemover);
                                                        System.out.println("%sProduto removido com sucesso.%s".formatted(Formatacao.GREEN.getCode(), Formatacao.RESET.getCode()));
                                                    } else {
                                                        System.out.println("%s● O produto não está associado a esta fatura.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                                    }
                                                }
                                            }
                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("%s● Entrada inválida! Por favor, introduza um número válido.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("%s● Erro ao alterar a data: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                            }
                        }
                        case 0 -> System.out.println("%s● Alteração cancelada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        default -> System.out.println("%s● Opção inválida.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    }
                    System.out.print("%s❯ Deseja alterar mais alguma informação? (S ou N): %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
                    String continuar = scanner.nextLine();
                    if (continuar.equalsIgnoreCase("N")) {
                        break;
                    }
                }
            } else {
                System.out.println("%s● A lista de faturas está vazia.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            }
        } catch (Exception e) {
            System.out.println("%s● Ocorreu um erro inesperado: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
        }
    }

    public void visualizarFatura() {
        System.out.print("%s❯ Qual é o número da fatura que quer vizualizar? %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        int num = lerInteiro();
        if (num != -1) {
            Fatura fatura = procurarFatura(num);
            if (fatura != null) {
                System.out.println(fatura.toStringFaturaFormatada());
            } else {
                System.out.println("%s● Fatura não encontrada.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            }
        }
    }

    public Fatura procurarFatura(int numFatura) {
        if (!listaFaturas.isEmpty()) {
            for (Fatura fatura : listaFaturas) {
                if (fatura.getNum() == numFatura) {
                    return fatura;
                }
            }
        }
        return null;
    }

    public int lerInteiro() {
        Scanner scanner = new Scanner(System.in);
        int num = -1;
        boolean valido = false;
        while (!valido) {
            try {
                System.out.print(" > ");
                num = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("%s● Entrada inválida. Por favor, introduza um número válido.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
            }
        }
        return num;
    }

    public int gerarNumFatura() {
        Random random = new Random();
        int novoNumero;
        boolean existe;

        // Gerar numeros aleatorios até não existir colisão com uma fatura existente
        do {
            novoNumero = random.nextInt(10000000);
            existe = procurarFatura(novoNumero) != null;
        } while (existe);

        return novoNumero;
    }

    public void apresentarEstatisticas() {
        int nFaturas = 0;
        int nProdutos = 0;
        double totalBruto = 0;
        double totalLiquido = 0;
        double totalIVA = 0;

        for (Fatura fatura : listaFaturas) {
            nFaturas++;

            for (Produto produto : fatura.getProdutos()) {
                nProdutos++;

                totalBruto += produto.preco * produto.quantidade;
                totalLiquido += (produto.preco * produto.quantidade) - ((produto.preco * produto.quantidade) * produto.calcularIva(fatura.getCliente()));
                totalIVA += (produto.preco * produto.quantidade) * produto.calcularIva(fatura.getCliente());

            }
        }

        System.out.printf("%s━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n", Formatacao.YELLOW.getCode());
        System.out.printf("%s%s                         Estatisticas:                          ", Formatacao.YELLOW.getCode(), Formatacao.BOLD.getCode());
        System.out.printf("\n      %-35s %s%-10d%s %-11s", "Número de Faturas", Formatacao.RESET.getCode(), nFaturas, Formatacao.YELLOW.getCode(), "");
        System.out.printf("\n      %-35s %s%-10d%s %-11s", "Número de Produtos", Formatacao.RESET.getCode(), nProdutos, Formatacao.YELLOW.getCode(), "");
        System.out.printf("\n      %-35s %s%-10.2f%s€ %-10s", "Total Bruto", Formatacao.RESET.getCode(), totalBruto, Formatacao.YELLOW.getCode(), "");
        System.out.printf("\n      %-35s %s%-10.2f%s€ %-10s", "Total Líquido", Formatacao.RESET.getCode(), totalLiquido, Formatacao.YELLOW.getCode(), "");
        System.out.printf("\n      %-35s %s%-10.2f%s€ %-10s\n", "Total IVA", Formatacao.RESET.getCode(), totalIVA, Formatacao.YELLOW.getCode(), "");
        System.out.printf("%s━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━%s\n", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());

    }
}
