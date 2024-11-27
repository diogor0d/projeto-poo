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
            System.out.println("As faturas foram escritas no ficheiro 'output.txt' com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever as faturas no ficheiro: " + e.getMessage());
        }
    }


    public void importarFaturas() {
        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            processarFaturas(br);
            System.out.println("Faturas importadas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao importar faturas do arquivo: " + e.getMessage());
        }
    }

    //Em vez de receber argumento, exporta as faturas na class POOFS
    public void escreverObjeto(ArrayList<Fatura> faturas, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        File ficheiroObjetos = new File("output.obj");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheiroObjetos))) {
            System.out.println("Arquivo " +ficheiroObjetos +" encontrado.");
            oos.writeObject(faturas);
            oos.writeObject(produtos);
            oos.writeObject(clientes);
            System.out.println("As listas foram escritas no ficheiro 'output.obj' com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao escrever as listas no ficheiro: " + e.getMessage());
        }
    }

    public void lerFicheiro() {
        File ficheiroObjetos = new File("output.obj");

        if (ficheiroObjetos.exists() && ficheiroObjetos.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheiroObjetos))) {
                System.out.println("Ficheiro output.obj encontrado.");

                ArrayList<Fatura> faturasLidas = (ArrayList<Fatura>) ois.readObject();
                ArrayList<Produto> produtosLidos = (ArrayList<Produto>) ois.readObject();
                ArrayList<Cliente> clientesLidos = (ArrayList<Cliente>) ois.readObject();

                faturas.setListaFaturas(faturasLidas);
                clientes.setListaClientes(clientesLidos);
                produtos.setListaProdutos(produtosLidos);

                System.out.println("Listas carregadas com sucesso.");
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Erro ao carregar dados do arquivo objeto: " + ex.getMessage());
            }
        } else {
            System.out.println("Ficheiro output.obj não encontrado.");
            File f_txt = new File("input.txt");
            if (f_txt.exists() && f_txt.isFile()) {
                System.out.println("Arquivo input.txt encontrado.");
                try (BufferedReader br = new BufferedReader(new FileReader(f_txt))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        processarLinha(line, br);
                    }
                    System.out.println("Processamento do arquivo input.txt concluído.");
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
                }
            } else {
                System.out.println("Ficheiro input.txt não encontrado.");
            }
        }
    }

    private void processarLinha(String line, BufferedReader br) throws IOException {
        System.out.println("Linha lida: " + line);
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
            System.out.println("Linha clientes: "+ line);
            String[] partes = line.split(",");
            try {
                String nome = partes[0].trim();
                int contribuinte = Integer.parseInt(partes[1].trim());
                clientes.procurarClientePorContribuinte(contribuinte);
                String localizacao = partes[2].trim();
                clientes.adicionarCliente(nome, contribuinte, localizacao);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar cliente: " + line);
            }
        }
    }



    private void processarProdutos(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            if (line.isEmpty()) continue;
            System.out.println("Linha produtos: "+ line);
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
                    System.out.println("Produto criado: " + produto);


                } else if (line.startsWith("PA_TR")) {
                    boolean isBiologico = Boolean.parseBoolean(elementos[6]);

                    // Criar lista de certificações
                    ArrayList<Certificacao> listaCertificacoes = new ArrayList<>();
                    for (int i = 7; i < elementos.length && listaCertificacoes.size() < 4; i++) {
                        try {
                            Certificacao certificacao = Certificacao.valueOf(elementos[i].trim());
                            listaCertificacoes.add(certificacao);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Certificação inválida ignorada: " + elementos[i]);
                        }
                    }

                    // Criar o produto
                    ProdutoAlimentarTR produto = new ProdutoAlimentarTR(codigo, nome, descricao, quantidade, preco, isBiologico, listaCertificacoes);
                    produtos.adicionarProduto(produto);
                    System.out.println("Produto criado: " + produto);


                } else if(line.startsWith("PA_TN")){
                    boolean isBiologico = Boolean.parseBoolean(elementos[6]);
                    ProdutoAlimentarTN produto = new ProdutoAlimentarTN(codigo, nome, descricao, quantidade, preco, isBiologico);
                    produtos.adicionarProduto(produto);

                } else if(line.startsWith("PF_NP")){
                    CategoriaFarmacia categoria = CategoriaFarmacia.valueOf(elementos[6]);
                    ProdutoFarmaciaNaoPrescrito produto = new ProdutoFarmaciaNaoPrescrito(codigo, nome, descricao, quantidade, preco, categoria);
                    produtos.adicionarProduto(produto);

                } else if(line.startsWith("PF_P")){
                    String medico = elementos[6];
                    ProdutoFarmaciaPrescrito produto = new ProdutoFarmaciaPrescrito(codigo, nome, descricao, quantidade, preco, medico);
                    produtos.adicionarProduto(produto);
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro ao processar número no ficheiro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao processar categoria ou booleano: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

        }
    }


    private void processarFaturas(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            if (line.isEmpty()) continue;
            System.out.println("Linha faturas: " + line);
            String[] partes = line.split(",");

            System.out.println(Formatacao.RED + Arrays.toString(partes) + Formatacao.RESET);


            for (int i = 0; i < partes.length; i++) {
                partes[i] = partes[i].trim();
            }

            try {
                int num = Integer.parseInt(partes[0]);
                Fatura fatura = faturas.procurarFatura(num);
                if(fatura != null){
                    System.out.println("Já existe uma fatura com o número " + num + "!");
                }
                else{
                    int contribuinte = Integer.parseInt(partes[1]);

                    Cliente cliente = clientes.procurarClientePorContribuinte(contribuinte);

                    if(cliente==null){
                        System.out.println("Cliente " + contribuinte + " não encontrado!");
                    } else{
                        String[] dataParts = partes[2].split("/");
                        int dia = Integer.parseInt(dataParts[0]);
                        int mes = Integer.parseInt(dataParts[1]);
                        int ano = Integer.parseInt(dataParts[2]);
                        Data data = new Data(dia, mes, ano);
                        if (!data.isDataValida()) {
                            System.out.println("Data incorreta. Tente novamente.");
                            break;
                        }

                        ArrayList<Produto> listaProdutos = new ArrayList<>();
                        for (int i = 3; i < partes.length; i++) {
                            String codigoProduto = partes[i];
                            Produto produto = produtos.procurarProdutoCodigo(Integer.parseInt(codigoProduto));
                            if (produto != null) {
                                listaProdutos.add(produto);
                            } else {
                                System.out.println("Produto não encontrado: " + codigoProduto);
                            }
                        }
                        if(!(listaProdutos.isEmpty())){
                            faturas.adicionarFatura(num, cliente, data, listaProdutos);
                        } else{
                            System.out.println("Lista de produtos vazia, fatura descartada");
                        }

                    }

                }

            } catch(NumberFormatException e){
                System.out.println("Erro ao processar cliente: " + line);
            }
        }
    }
}


