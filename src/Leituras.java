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

    public void lerArquivo() {
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
            File f_txt = new File("input.txt");
            if (f_txt.exists() && f_txt.isFile()) {
                System.out.println("Arquivo input.txt encontrado.");

                try {
                    // Separando a leitura em FileReader e BufferedReader
                    FileReader fr = new FileReader(f_txt);
                    BufferedReader br = new BufferedReader(fr);
                    String line;

                    while ((line = br.readLine()) != null) {
                        line = line.trim(); // Remove espaços extras no começo e no fim
                        if (line.isEmpty()) {
                            continue;
                        }
                        //clientes:
                        while (line.equalsIgnoreCase("faturas") && !line.isEmpty()) {
                            String partes[] = line.split(",");
                            if (partes.length == 3) {
                                String nome = partes[0].trim();
                                int contribuinte = Integer.parseInt(partes[1].trim());
                                String localizacao = partes[2].trim();

                                // Adiciona o cliente
                                clientes.adicionarCliente(nome, contribuinte, localizacao);
                                System.out.println("Faturas carregadas com sucesso.");
                            }
                        }
                        while (line.equalsIgnoreCase("produtos") && !line.isEmpty()) {
                            String partes[] = line.split(",");
                            if (partes.length >= 3) {
                                int numero = Integer.parseInt(partes[0].trim());
                                String clienteNome = partes[1].trim();
                                String[] dataPartes = partes[2].trim().split("/");
                                int dia = Integer.parseInt(dataPartes[0]);
                                int mes = Integer.parseInt(dataPartes[1]);
                                int ano = Integer.parseInt(dataPartes[2]);
                                Data data = new Data(dia, mes, ano);

                                //Fatura fatura = new Fatura(numero, clienteNome, data,);

                                //faturas.adicionarFatura(fatura);
                                //faturas.setListaFaturas(listaFaturas);
                                System.out.println("Faturas carregadas com sucesso.");
                            }
                        }
                        //codigo para ler os produtos
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