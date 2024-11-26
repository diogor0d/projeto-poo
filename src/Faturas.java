//vizualizarFatura e editarFatura estão incompletos

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
                System.out.println("Lista de faturas atualizada com sucesso.");
            } else {
                System.out.println("A nova lista de faturas é inválida (null).");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }



    // Metodo para ler os dados e criar a fatura
    public void novaFatura() {
        Scanner scanner = new Scanner(System.in);

        int numero;
        while (true) {
            System.out.print("\nDeseja introduzir um número especifico para a fatura? (S ou N): ");
            String inserirNumManual = scanner.nextLine();
            if (inserirNumManual.equalsIgnoreCase("N")) {
                // Gerar e atribuir um número para a fatura de forma automaticac
                numero = gerarNumFatura();
                System.out.println("Foi atribuído o seguinte número à sua fatura: " + numero);
                break;
            } else if (inserirNumManual.equalsIgnoreCase("S")) {
                // Permitir ao utilizador atribuir um número para a fatura
                while (true) {
                    try {
                        System.out.print("Digite o número da fatura: ");
                        numero = Integer.parseInt(scanner.nextLine());
                        Fatura faturaEncontrada = procurarFatura(numero);
                        if(faturaEncontrada == null){
                            break;
                        } else{
                            System.out.println("Já existe uma fatura com esse número!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Digite um número inteiro.");
                    }
                }
                break;
            } else {
                System.out.println("Entrada inválida. Digite apenas 'S' ou 'N'.");
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
                data = new Data(dia, mes, ano);
                if (data.isDataValida()) {
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

        produtos.listarProdutos(produtos.getListaProdutos());
        System.out.print("Insira os códigos dos produtos a adicionar (separados por ','): ");

        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String line = scanner.nextLine();
        String[] partes = line.split(",");
        for (int i = 0; i < partes.length; i++) {
            partes[i] = partes[i].trim();
        }
        try {
            for (String codigo : partes) {
                Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(codigo));
                if (produto != null) {
                    System.out.println("Produto " + produto.getNome() + " adicionado.");
                    listaProdutos.add(produto);
                } else {
                    System.out.println("Produto com o código " + codigo + " não encontrado. Verifique o código e tente adiciona-lo novamente.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro ao processar os produtos: " + line);
        }

        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto registado na fatura. Criação de fatura abortada.");
            return;
        }


        try {
            adicionarFatura(numero, cliente, data, listaProdutos);
            System.out.println("Fatura criada com sucesso.");
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
    //falta mudar os produtos!!
    public void editarFatura() {
        Scanner scanner = new Scanner(System.in);
        try {
            if (!listaFaturas.isEmpty()) {
                Fatura faturaEncontrada;
                while (true) {
                    System.out.print("Qual é o número da fatura, à qual quer alterar os dados? ");
                    int num = Integer.parseInt(scanner.nextLine());
                    faturaEncontrada = procurarFatura(num);
                    if (faturaEncontrada != null) {
                        System.out.print("Fatura " + num + " encontrada.");
                        break;
                    } else {
                        System.out.println("Não existe nenhuma fatura com esse número!");
                    }
                }
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
                                faturaEncontrada.setNumero(novoNumero);
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
                                    faturaEncontrada.setCliente(novoCliente);
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
                                    faturaEncontrada.setData(novaData);
                                    System.out.println("Data alterada com sucesso.");
                                } else {
                                    System.out.println("Formato de data inválido.");
                                }
                            } catch (Exception e) {
                                System.out.println("Erro ao alterar a data: " + e.getMessage());
                            }
                            break;
                        case 4:
                            try{
                                ArrayList<Produto> produtosDaFatura = faturaEncontrada.getProdutos();
                                if (produtosDaFatura == null || produtosDaFatura.isEmpty()) {
                                    System.out.println("Não há produtos associados a esta fatura.");
                                }
                                int opcaoProdutos = -1;
                                while (opcaoProdutos != 0) {
                                    produtos.listarProdutos(produtosDaFatura);
                                    System.out.print("\nDeseja:\n1- Adicionar um produto\n2- Remover um produto\n0- Cancelar\nOpção-> ");
                                    try {
                                        opcaoProdutos = Integer.parseInt(scanner.nextLine());
                                        if (opcaoProdutos < 0 || opcaoProdutos > 2) {
                                            System.out.println("Opção inválida! Por favor, digite um número entre 0 e 2.");
                                            continue;
                                        }
                                        switch (opcaoProdutos){
                                            case 1:
                                                System.out.print("Insira o código do produto que deseja adicionar: ");
                                                String codigoProdutoAdicionar = scanner.nextLine().trim();
                                                Produto produtoAdicionar = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProdutoAdicionar));
                                                if (produtoAdicionar != null) {
                                                    produtosDaFatura.add(produtoAdicionar);
                                                    System.out.println("Produto adicionado com sucesso.");
                                                }
                                                break;
                                            case 2:
                                                System.out.print("Insira o codigo do produto a remover: ");
                                                String codigoProdutoRemover = scanner.nextLine().trim();
                                                Produto produtoRemover = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProdutoRemover));
                                                if (produtoRemover != null) {
                                                    if (produtosDaFatura.contains(produtoRemover)) {
                                                        produtosDaFatura.remove(produtoRemover);
                                                        System.out.println("Produto removido com sucesso.");
                                                    } else {
                                                        System.out.println("O produto não está associado a esta fatura.");
                                                    }
                                                }

                                                break;
                                        }

                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada inválida! Por favor, digite um número válido.");
                                    }
                                }

                            }catch (Exception e) {
                                System.out.println("Erro ao alterar a data: " + e.getMessage());
                            }
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
            } else {
                System.out.println("A lista de faturas está vazia.");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public void visualizarFatura() {
        System.out.print("Qual é o número da fatura que quer vizualizar? ");
        int num = lerInteiro();
        if (num != -1) {
            Fatura fatura = procurarFatura(num);
            if (fatura != null) {
                apresentarFatura(fatura);
            } else {
                System.out.println("Fatura não encontrada.");
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
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(" > ");
                num = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }
        return num;
    }

    public void apresentarFatura(Fatura fatura) {
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("|  Fatura simplificada nº:  %-80d |\n", fatura.getNum());
        Cliente cliente = fatura.getCliente();
        System.out.printf("|  Cliente:                 %-80s |\n", cliente.getNome());
        System.out.printf("|  Nº Contribuinte:         %-80d | \n", cliente.getContribuinte());
        System.out.printf("|  Data:                    %-80s |\n", fatura.getData());
        System.out.printf("|  Região:                  %-80s |\n", fatura.getCliente().getLocalizacao());
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println("QNT | Produto                                   |  Preço    |  Taxa   | Valor IVA | Subtotal  | Subtotal Taxas");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        double totalIva = 0;
        for (Produto produto : fatura.getProdutos()) {
            double subtotal = produto.getPreco() * produto.getQuantidade();
            System.out.printf(" %-3d| %-42s| %-8.2f€ | %-6.2f%% | %-6.2f €  | %-8.2f€ |  %-8.2f€   |\n",
                    produto.getQuantidade(),
                    produto.getNome(),
                    produto.getPreco(),
                    produto.calcularIva(cliente) * 100,
                    produto.getPreco() * produto.calcularIva(cliente),
                    subtotal,
                    subtotal + (subtotal * produto.calcularIva(cliente))
            );
            totalIva += subtotal + (subtotal * produto.calcularIva(cliente));
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.printf("                Total s/IVA: %.2f€ | Total IVA: %.2f€ | Total c/IVA: %.2f€\n", fatura.calcularTotalBruto(), totalIva-fatura.calcularTotalBruto() , totalIva);
        System.out.println("--------------------------------------------------------------------------------------------------------------");
    }

    public int gerarNumFatura() {
        Random random = new Random();
        int novoNumero;
        boolean existe;

        // Gerar numeros aleatorios até não existir colisão com uma fatura existente
        do {
            novoNumero = random.nextInt(10000000);
            existe = false;
            if (procurarFatura(novoNumero) != null) {
                existe = true;
            }
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

        System.out.print("\nEstatisticas:");
        System.out.printf("\n%-20s: %d", "Número de Faturas", nFaturas);
        System.out.printf("\n%-20s: %d", "Número de Produtos", nProdutos);
        System.out.printf("\n%-20s: %.2f€", "Total Bruto", totalBruto);
        System.out.printf("\n%-20s: %.2f€", "Total Líquido", totalLiquido);
        System.out.printf("\n%-20s: %.2f€\n", "Total IVA", totalIVA);

    }
}
