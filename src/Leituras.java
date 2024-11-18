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

    public void lerArquivo(){
        File f_obj = new File("output.obj");

        if(f_obj.exists() && f_obj.isFile()) {
            try{
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
            }catch (ClassNotFoundException ex) {
                System.out.println("Erro: Classe não encontrada durante a leitura do objeto.");
            }


        }
        //Ler o ficheiro de texto
        else{
            File f_txt = new File("input.txt");
            if(f_txt.exists() && f_txt.isFile()){
                System.out.println("Arquivo input.txt encontrado.");







            }
            else{
                System.out.println("Nenhum arquivo encontrado.");
            }
        }
    }
}
