// duvida: colocamos as linhas 7 e 8 como final??

import java.io.*;
import java.util.*;

public class Leituras {
    private final Clientes clientes;
    private final Faturas faturas;
    private final Produtos produtos;

    public Leituras(Clientes clientes, Faturas faturas, Produtos produtos) {
        this.clientes = clientes;
        this.faturas = faturas;
        this.produtos = produtos;
    }


    //Em vez de receber argumento, exporta as faturas na class Faturas e ta feito
    public void exportarFaturas(ArrayList<Fatura> faturas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            for (Fatura fatura : faturas) {
                writer.write(fatura.toStringFicheiro());
                writer.newLine();
            }
            System.out.printf("%s● As faturas foram escritas no ficheiro %s'output.txt'%s com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
        } catch (IOException e) {
            System.out.printf("%s● Erro ao escrever as faturas no ficheiro: %s %s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }


    public void importarFaturas() {
        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            processarFaturas(br);
            System.out.printf("%s● Faturas importadas com sucesso.%s\n", Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());
        } catch (IOException e) {
            System.out.printf("%s● Erro ao importar faturas do arquivo: %s%s\n ", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    //Em vez de receber argumento, exporta as faturas na class POOFS
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

    public void lerFicheiro() {
        File ficheiroObjetos = new File("output.obj");

        if (ficheiroObjetos.exists() && ficheiroObjetos.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiroObjetos))) {
                System.out.printf("%s● Ficheiro %s\'output.obj\'%s encontrado.%s\n", Formatacao.GREEN.getCode(), Formatacao.YELLOW.getCode(), Formatacao.GREEN.getCode(), Formatacao.RESET.getCode());

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
                            case "CL":
                                processarCliente(linhaSplitted);
                                break;
                            case "F":
                                processarFatura(linhaSplitted);
                                break;
                            default:
                                processarProduto(linhaSplitted);
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
            System.out.printf("%s● Erro inesperado: %d%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

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
            for (int i = 4; i < splittedLine.length; i++) {
                Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(splittedLine[i]));
                if (produto != null) {
                    listaProdutos.add(produto);
                } else {
                    System.out.printf("%s● Produto não encontrado: %d%s\n", Formatacao.RED.getCode(), Integer.parseInt(splittedLine[i]), Formatacao.RESET.getCode());
                }
            }
            if (listaProdutos.isEmpty()) {
                throw new IllegalArgumentException(String.format("Lista de produtos vazia, fatura %d descartada.", num));
            }
            if (cliente == null) {
                throw new IllegalArgumentException(String.format("Erro ao reconhecer cliente an fatura %d.", num));
            }


            faturas.adicionarFatura(num, cliente, data, listaProdutos);
        } catch (NumberFormatException e) {
            System.out.printf("%s● Erro ao processar fatura %d: %s%s\n", Formatacao.RED.getCode(), splittedLine, Formatacao.RESET.getCode());
        } catch (IllegalArgumentException e) {
            System.out.printf("%s● Erro ao processar fatura %d: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }
    }

    private void processarCliente(String[] splittedLine) {
        try {
            String nome = splittedLine[1];
            int contribuinte = Integer.parseInt(splittedLine[2]);
            String localizacao = splittedLine[3];
            clientes.adicionarCliente(nome, contribuinte, localizacao);
        } catch (NumberFormatException e) {
            System.out.printf("%s● Erro ao processar cliente: %s%s\n", Formatacao.RED.getCode(), splittedLine, Formatacao.RESET.getCode());
        }
    }

    private void processarLinha(String line, BufferedReader br) throws IOException {


        if (line.equalsIgnoreCase("clientes")) {
            processarClientes(br);
        } else if (line.equalsIgnoreCase("produtos")) {
            processarProdutos(br);
        } else if (line.equalsIgnoreCase("faturas")) {
            processarFaturas(br);
        }
    }

    private void processarClientes(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            if (line.isEmpty()) continue;
            String[] partes = line.split(",");
            try {
                String nome = partes[0].trim();
                int contribuinte = Integer.parseInt(partes[1].trim());
                clientes.procurarClientePorContribuinte(contribuinte);
                String localizacao = partes[2].trim();
                clientes.adicionarCliente(nome, contribuinte, localizacao);
            } catch (NumberFormatException e) {
                System.out.printf("%s● Erro ao processar cliente: %s%s\n", Formatacao.RED.getCode(), line, Formatacao.RESET.getCode());
            }
        }
    }


    private void processarProdutos(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            if (line.isEmpty()) continue;
            line = line.trim();
            String[] elementos = line.split(",");
            for (int i = 0; i < elementos.length; i++) {
                elementos[i] = elementos[i].trim();
            }
            try {

                int codigo = Integer.parseInt(elementos[1]);
                String nome = elementos[2];
                String descricao = elementos[3];
                int quantidade = Integer.parseInt(elementos[4]);
                double preco = Double.parseDouble(elementos[5]);


                if (line.startsWith("PA_TI")) {
                    boolean isBiologico = Boolean.parseBoolean(elementos[6]);
                    CategoriaAlimentar categoria = CategoriaAlimentar.valueOf(elementos[7]);

                    ProdutoAlimentarTI produto = new ProdutoAlimentarTI(codigo, nome, descricao, quantidade, preco, isBiologico, categoria);
                    produtos.adicionarProduto(produto);


                } else if (line.startsWith("PA_TR")) {
                    boolean isBiologico = Boolean.parseBoolean(elementos[6]);

                    // Criar lista de certificações
                    ArrayList<Certificacao> listaCertificacoes = new ArrayList<>();
                    for (int i = 7; i < elementos.length && listaCertificacoes.size() < 4; i++) {
                        try {
                            Certificacao certificacao = Certificacao.valueOf(elementos[i].trim());
                            listaCertificacoes.add(certificacao);
                        } catch (IllegalArgumentException e) {
                            System.out.printf("%s● Certificação inválida ignorada: %s%s\n", Formatacao.RED.getCode(), elementos[i], Formatacao.RESET.getCode());
                        }
                    }

                    // Criar o produto
                    ProdutoAlimentarTR produto = new ProdutoAlimentarTR(codigo, nome, descricao, quantidade, preco, isBiologico, listaCertificacoes);
                    produtos.adicionarProduto(produto);


                } else if (line.startsWith("PA_TN")) {
                    boolean isBiologico = Boolean.parseBoolean(elementos[6]);
                    ProdutoAlimentarTN produto = new ProdutoAlimentarTN(codigo, nome, descricao, quantidade, preco, isBiologico);
                    produtos.adicionarProduto(produto);

                } else if (line.startsWith("PF_NP")) {
                    CategoriaFarmacia categoria = CategoriaFarmacia.valueOf(elementos[6]);
                    ProdutoFarmaciaNaoPrescrito produto = new ProdutoFarmaciaNaoPrescrito(codigo, nome, descricao, quantidade, preco, categoria);
                    produtos.adicionarProduto(produto);

                } else if (line.startsWith("PF_P")) {
                    String medico = elementos[6];
                    ProdutoFarmaciaPrescrito produto = new ProdutoFarmaciaPrescrito(codigo, nome, descricao, quantidade, preco, medico);
                    produtos.adicionarProduto(produto);
                }

            } catch (NumberFormatException e) {
                System.out.printf("%s● Erro ao processar número no ficheiro: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
            } catch (IllegalArgumentException e) {
                System.out.printf("%s● Erro ao processar categoria ou booleano: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
            } catch (Exception e) {
                System.out.printf("%s● Erro inesperado: %d%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
            }

        }
    }


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


