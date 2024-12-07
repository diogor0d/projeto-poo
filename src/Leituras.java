
import java.io.*;
import java.util.*;

/**
 * Classe que representa as leituras de ficheiros e escritas de ficheiros, através de métodos e as respetivas estruturas para gerir o sistema de faturas.
 */
public class Leituras {
    /**
     * Lista de clientes
     */
    private final Clientes clientes;

    /**
     * Lista de faturas
     */
    private final Faturas faturas;

    /**
     * Lista de produtos
     */
    private final Produtos produtos;

    /**
     * Construtor da classe Leituras
     * @param clientes Lista de clientes
     * @param faturas Lista de faturas
     * @param produtos Lista de produtos
     */
    public Leituras(Clientes clientes, Faturas faturas, Produtos produtos) {
        this.clientes = clientes;
        this.faturas = faturas;
        this.produtos = produtos;
    }


    /**
     * Método que exporta as faturas para um ficheiro de texto especificado e solicitado ao utilizador
     * @param faturas Lista de faturas
     */
    public void exportarFaturas(ArrayList<Fatura> faturas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("%s❯ Insira o nome do ficheiro de exportação: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        String fileName = scanner.nextLine();

        if(!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Fatura fatura : faturas) {
                writer.write(fatura.toStringFicheiro());
                writer.newLine();
            }
            System.out.printf("%s● As faturas foram escritas no ficheiro %s'%s'%s com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), fileName,Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
        } catch (IOException e) {
            System.out.printf("%s● Erro ao escrever as faturas no ficheiro: %s %s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que importa as faturas de um ficheiro de texto especificado e solicitado ao utilizador
     */
    public void importarFaturas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("%s❯ Insira o nome do ficheiro a importar: %s".formatted(Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode()));
        String fileName = scanner.nextLine();

        if(!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        File ficheiroImportacao = new File(fileName);

        if(!ficheiroImportacao.exists() || !ficheiroImportacao.isFile()) {
            System.out.printf("%s● Ficheiro %s'%s'%s não encontrado.%s\n", Formatacao.RED.getCode(), Formatacao.YELLOW.getCode(), fileName, Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            processarFaturas(br);
            System.out.printf("%s● Faturas importadas com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
        } catch (IOException e) {
            System.out.printf("%s● Erro ao importar faturas do ficheiro: %s%s\n ", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que exporta as faturas para um ficheiro de objetos 'output.obj'
     * @param faturas Lista de faturas
     * @param produtos Lista de produtos
     * @param clientes Lista de clientes
     */
    public void escreverObjeto(ArrayList<Fatura> faturas, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        File ficheiroObjetos = new File("output.obj");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheiroObjetos))) {
            System.out.printf("%s● Ficheiro %s'" + ficheiroObjetos + "'%s encontrado.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
            oos.writeObject(faturas);
            oos.writeObject(produtos);
            oos.writeObject(clientes);
            System.out.printf("%s● As listas foram escritas no ficheiro %s'output.obj'%s com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
        } catch (IOException e) {
            System.out.printf("%s● Erro ao escrever as listas no ficheiro: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que lê as listas de faturas, produtos e clientes de um ficheiro de objetos 'output.obj'
     */
    public void lerFicheiro() {
        File ficheiroObjetos = new File("output.obj");

        if (ficheiroObjetos.exists() && ficheiroObjetos.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiroObjetos))) {
                System.out.printf("%s● Ficheiro %s'output.obj'%s encontrado.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());

                ArrayList<Fatura> faturasLidas = (ArrayList<Fatura>) ois.readObject();
                ArrayList<Produto> produtosLidos = (ArrayList<Produto>) ois.readObject();
                ArrayList<Cliente> clientesLidos = (ArrayList<Cliente>) ois.readObject();

                faturas.setListaFaturas(faturasLidas);
                clientes.setListaClientes(clientesLidos);
                produtos.setListaProdutos(produtosLidos);

                System.out.printf("%s● Listas carregadas com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
            } catch (IOException | ClassNotFoundException ex) {
                System.out.printf("%s● Erro ao carregar dados do ficheiro de objetos: %s %s\n", Formatacao.RED.getCode(), ex.getMessage(), Formatacao.RESET.getCode());
            }
        } else {
            System.out.printf("%s● Ficheiro %soutput.obj%s não encontrado.%s\n", Formatacao.RED.getCode(), Formatacao.YELLOW.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            File f_txt = new File("input.txt");
            if (f_txt.exists() && f_txt.isFile()) {
                System.out.printf("%s● Ficheiro %sinput.txt%s encontrado.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
                try (BufferedReader br = new BufferedReader(new FileReader(f_txt))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        if (line.isEmpty()) continue;
                        String[] linhaSplitted = line.split(",");
                        for (int i = 0; i < linhaSplitted.length; i++) {
                            linhaSplitted[i] = linhaSplitted[i].trim();
                        }
                        switch (linhaSplitted[0].toUpperCase()) {
                            case "CL" -> processarCliente(linhaSplitted);
                            case "F" -> processarFatura(linhaSplitted);
                            default -> processarProduto(linhaSplitted);
                        }
                    }
                    System.out.printf("%s● Processamento do ficheiro %sinput.txt%s concluído.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
                } catch (IOException e) {
                    System.out.printf("%s● Erro ao ler o ficheiro de texto: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
                }
            } else {
                System.out.printf("%s● Ficheiro %sinput.txt%s não encontrado.%s\n", Formatacao.RED.getCode(), Formatacao.YELLOW.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            }
        }
    }

    /**
     * Método que processa um produto a partir de uma linha do ficheiro de texto
     * @param splittedLine Linha do ficheiro de texto
     */
    private void processarProduto(String[] splittedLine) {
        try {

            int codigo = Integer.parseInt(splittedLine[1]);
            String nome = splittedLine[2];
            String descricao = splittedLine[3];
            int quantidade = Integer.parseInt(splittedLine[4]);
            double preco = Double.parseDouble(splittedLine[5]);
            if (splittedLine[0].equalsIgnoreCase("PA_TI")) {
                boolean isBiologico = Boolean.parseBoolean(splittedLine[6]);
                CategoriaAlimentar categoria = CategoriaAlimentar.valueOf(splittedLine[7]);

                ProdutoAlimentarTI produto = new ProdutoAlimentarTI(codigo, nome, descricao, quantidade, preco, isBiologico, categoria);
                produtos.adicionarProduto(produto);


            } else if (splittedLine[0].equalsIgnoreCase("PA_TR")) {
                boolean isBiologico = Boolean.parseBoolean(splittedLine[6]);

                // Criar lista de certificações
                ArrayList<Certificacao> listaCertificacoes = new ArrayList<>();
                for (int i = 7; i < splittedLine.length && listaCertificacoes.size() < 4; i++) {
                    try {
                        Certificacao certificacao = Certificacao.valueOf(splittedLine[i].trim());
                        listaCertificacoes.add(certificacao);
                    } catch (IllegalArgumentException e) {
                        System.out.printf("%s● Certificação inválida ignorada: %s%s\n", Formatacao.RED.getCode(), splittedLine[i], Formatacao.RESET.getCode());
                    }
                }

                // Criar o produto
                ProdutoAlimentarTR produto = new ProdutoAlimentarTR(codigo, nome, descricao, quantidade, preco, isBiologico, listaCertificacoes);
                produtos.adicionarProduto(produto);


            } else if (splittedLine[0].equalsIgnoreCase("PA_TN")) {
                boolean isBiologico = Boolean.parseBoolean(splittedLine[6]);
                ProdutoAlimentarTN produto = new ProdutoAlimentarTN(codigo, nome, descricao, quantidade, preco, isBiologico);
                produtos.adicionarProduto(produto);

            } else if (splittedLine[0].equalsIgnoreCase("PF_NP")) {
                CategoriaFarmacia categoria = CategoriaFarmacia.valueOf(splittedLine[6]);
                ProdutoFarmaciaNaoPrescrito produto = new ProdutoFarmaciaNaoPrescrito(codigo, nome, descricao, quantidade, preco, categoria);
                produtos.adicionarProduto(produto);

            } else if (splittedLine[0].equalsIgnoreCase("PF_P")) {
                String medico = splittedLine[6];
                ProdutoFarmaciaPrescrito produto = new ProdutoFarmaciaPrescrito(codigo, nome, descricao, quantidade, preco, medico);
                produtos.adicionarProduto(produto);
            }
        } catch (NumberFormatException e) {
            System.out.printf("%s● Erro ao processar número no ficheiro: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        } catch (IllegalArgumentException e) {
            System.out.printf("%s● Erro ao processar categoria ou booleano: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("%s● Erro inesperado: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que processa uma fatura a partir de uma linha do ficheiro de texto
     * @param splittedLine Linha do ficheiro de texto
     */
    private void processarFatura(String[] splittedLine) {
        try {
            int num = Integer.parseInt(splittedLine[1]);
            Cliente cliente = clientes.procurarClientePorContribuinte(Integer.parseInt(splittedLine[2]));
            String[] dataParts = splittedLine[3].split("/");
            int dia = Integer.parseInt(dataParts[0]);
            int mes = Integer.parseInt(dataParts[1]);
            int ano = Integer.parseInt(dataParts[2]);
            Data data = new Data(dia, mes, ano);
            ArrayList<Produto> listaProdutos = new ArrayList<>();
            if (!(splittedLine.length >= 5)) {
                throw new IllegalArgumentException(String.format("%s● Fatura %d descartada, não estão declarados produtos.%s\n", Formatacao.RED.getCode(), num, Formatacao.RESET.getCode()));
            }
            for (int i = 4; i < splittedLine.length; i++) {
                Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(splittedLine[i]));
                if (produto != null) {
                    listaProdutos.add(produto);
                } else {
                    System.out.printf("%s● Produto não encontrado: %d; Produto ignorado ao processar a fatura %d%s\n", Formatacao.RED.getCode(), Integer.parseInt(splittedLine[i]), num, Formatacao.RESET.getCode());
                }
            }

            if (listaProdutos.isEmpty()) {
                throw new IllegalArgumentException(String.format("Lista de produtos vazia, fatura %d descartada.", num));
            }
            if (cliente == null) {
                throw new IllegalArgumentException(String.format("Erro ao reconhecer cliente na fatura %d.", num));
            }


            faturas.adicionarFatura(num, cliente, data, listaProdutos);
        } catch (IllegalArgumentException e) {
            System.out.printf("%s● Erro ao processar fatura: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que processa um cliente a partir de uma linha do ficheiro de texto
     * @param splittedLine Linha do ficheiro de texto
     */
    private void processarCliente(String[] splittedLine) {
        try {
            String nome = splittedLine[1];
            int contribuinte = Integer.parseInt(splittedLine[2]);
            String localizacao = splittedLine[3];
            clientes.adicionarCliente(nome, contribuinte, localizacao);
        } catch (NumberFormatException e) {
            System.out.printf("%s● Erro ao processar cliente: %s%s\n", Formatacao.RED.getCode(), Arrays.toString(splittedLine), Formatacao.RESET.getCode());
        }
    }

    /**
     * Método que processa as faturas a partir de um ficheiro de texto exportado previamente ou criado manualmente
     * @param br BufferedReader
     * @throws IOException Exceção de I/O
     */
    private void processarFaturas(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            if (line.isEmpty()) continue;
            String[] partes = line.split(",");

            for (int i = 0; i < partes.length; i++) {
                partes[i] = partes[i].trim();
            }

            try {
                int num = Integer.parseInt(partes[0]);
                Fatura fatura = faturas.procurarFatura(num);
                if (fatura != null) {
                    System.out.printf("%s● Fatura %d já existe!%s\n", Formatacao.RED.getCode(), num, Formatacao.RESET.getCode());
                } else {
                    int contribuinte = Integer.parseInt(partes[1]);

                    Cliente cliente = clientes.procurarClientePorContribuinte(contribuinte);

                    if (cliente == null) {
                        System.out.printf("%s● Cliente %d não encontrado!%s\n", Formatacao.RED.getCode(), contribuinte, Formatacao.RESET.getCode());
                    } else {
                        String[] dataParts = partes[2].split("/");
                        int dia = Integer.parseInt(dataParts[0]);
                        int mes = Integer.parseInt(dataParts[1]);
                        int ano = Integer.parseInt(dataParts[2]);
                        Data data = new Data(dia, mes, ano);
                        if (!data.isDataValida()) {
                            System.out.printf("%s● Data incorreta. Tente novamente.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                            break;
                        }

                        ArrayList<Produto> listaProdutos = new ArrayList<>();
                        for (int i = 3; i < partes.length; i++) {
                            String codigoProduto = partes[i];
                            Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProduto));
                            if (produto != null) {
                                listaProdutos.add(produto);
                            } else {
                                System.out.printf("%s● Produto não encontrado: %d%s\n", Formatacao.RED.getCode(), Integer.parseInt(codigoProduto), Formatacao.RESET.getCode());
                            }
                        }
                        if (!(listaProdutos.isEmpty())) {
                            faturas.adicionarFatura(num, cliente, data, listaProdutos);
                        } else {
                            System.out.printf("%s● Lista de produtos vazia, fatura descartada.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                        }

                    }

                }

            } catch (NumberFormatException e) {
                System.out.printf("%s● Erro ao processar cliente: %s%s\n", Formatacao.RED.getCode(), line, Formatacao.RESET.getCode());
            }
        }
    }
}


