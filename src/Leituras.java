// duvida: colocamos as linhas 7 e 8 como final??

import java.io.*;
import java.util.*;

public class Leituras {
    private Clientes clientes;
    private Faturas faturas;

    // Construtor que recebe as instâncias de Clientes e Faturas
    public Leituras(Clientes clientes, Faturas faturas) {
        this.clientes = clientes;
        this.faturas = faturas;
    }

    public void lerFicheiro() {
        File f_obj = new File("output.obj");

        if (f_obj.exists() && f_obj.isFile()) {
            try {
                System.out.println("Arquivo output.obj encontrado.");
                FileInputStream is = new FileInputStream(f_obj);
                ObjectInputStream ois = new ObjectInputStream(is);

                // Ler lista de clientes do arquivo
                ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) ois.readObject();

                // Ler a lista de faturas
                ArrayList<Fatura> listaFaturas = (ArrayList<Fatura>) ois.readObject();

                clientes.setListaClientes(listaClientes);
                faturas.setListaFaturas(listaFaturas);

                // Fechar o stream
                ois.close();
                System.out.println("Listas carregadas com sucesso.");

            } catch (IOException ex) {
                System.out.println("Erro ao ler ficheiro: " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Erro: Classe não encontrada durante a leitura do objeto.");
            }
        }
        // Ler o ficheiro de texto
        else {
            File f_txt = new File("ds");
            if (f_txt.exists() && f_txt.isFile()) {
                System.out.println("Arquivo input.txt encontrado.");

                try {
                    FileReader fr = new FileReader(f_txt);
                    BufferedReader br = new BufferedReader(fr);
                    String line;

                    while ((line = br.readLine()) != null) {
                        line = line.trim(); // Remove espaços extras no começo e no fim

                        //leitura dos clientes:
                        if (line.equalsIgnoreCase("clientes")) {
                            while (!(line.equalsIgnoreCase("faturas"))) {
                                if (line.isEmpty()) continue;
                                line = line.trim();

                                String partes[] = line.split(",");
                                if (partes.length == 3) {
                                    try {
                                        String nome = partes[0].trim();
                                        int contribuinte = Integer.parseInt(partes[1].trim());
                                        String localizacao = partes[2].trim();

                                        // Adiciona o cliente
                                        clientes.adicionarCliente(nome, contribuinte, localizacao);
                                        System.out.println("Faturas carregadas com sucesso.");
                                    } catch (NumberFormatException e) {
                                        System.out.println("Erro ao processar cliente: " + line);
                                    }
                                }
                            }
                        }
                        //leitura dos produtos RODASSSSS
                        if (line.equalsIgnoreCase("produtos")) {
                            while (!(line.equalsIgnoreCase("faturas"))) {
                                if (line.isEmpty()) continue;
                                line = line.trim();
                                if (line.startsWith("TAXA_INTERMEDIA")) {

                                } else if (line.startsWith("TAXA_REDUZIDA")) {

                                } else if (line.startsWith("TAXA_NOMAL")) {

                                } else if (line.startsWith("COM_PRESCRICAO")) {

                                } else if (line.startsWith("SEM_PRESCRICAO")) {

                                }
                            }
                        }

                        //RODAS aqui ve se adicionas os produtos so com o nome no ficheiro de texto e depois vais ver à
                        //lista de produtos para ir buscar o produto ou se les logo tudo..
                        //desde que no final adiciones a lista de produtos dessa fatura td okay
                        if (line.equalsIgnoreCase("faturas")) {
                            while (!(line.equalsIgnoreCase("produtos"))) {
                                if (line.isEmpty()) continue;
                                line = line.trim();

                                String[] partes = line.split(",");
                                if (partes.length >= 3) {
                                    try {
                                        int numero = Integer.parseInt(partes[0].trim());
                                        int contribuinte = Integer.parseInt(partes[1].trim());
                                        Cliente cliente = clientes.procurarClientePorContribuinte(contribuinte);

                                        if (cliente == null) {
                                            break;
                                        }

                                        String[] dataPartes = partes[2].trim().split("/");
                                        int dia = Integer.parseInt(dataPartes[0]);
                                        int mes = Integer.parseInt(dataPartes[1]);
                                        int ano = Integer.parseInt(dataPartes[2]);
                                        Data data = new Data(dia, mes, ano);

                                        ArrayList<Produto> produtos = new ArrayList<>();
                                        for (int i = 3; i < partes.length; i++) {
                                            //produtos.add(new Produto(partes[i].trim()));
                                        }
                                        faturas.adicionarFatura(numero, cliente, data, produtos);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Erro ao processar fatura: " + line);
                                    }
                                }
                            }
                        }
                        System.out.println("Faturas carregadas com sucesso.");

                        if (line.equalsIgnoreCase("produtos")) {
                            while ((line = br.readLine()) != null) {
                                line = line.trim();
                                if (line.equalsIgnoreCase("fim")) break;


                                String[] atributos = line.split(",");
                                if (atributos.length == 5) {
                                    try {
                                        int codigo = Integer.parseInt(atributos[0].trim());
                                        String nome = atributos[1].trim();
                                        String descricao = atributos[2].trim();
                                        int quantidade = Integer.parseInt(atributos[3].trim());
                                        double preco = Double.parseDouble(atributos[4].trim());

                                        Produto produto = new Produto(codigo, nome, descricao, quantidade, preco);
                                        //produtos.add(produto);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Erro ao processar produto: " + line);
                                    }
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo de texto: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }

            }
        }
    }
}
