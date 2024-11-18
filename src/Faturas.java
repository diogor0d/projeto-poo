import java.util.ArrayList;
import java.util.Scanner;

public class Faturas{
    private ArrayList<Fatura> listaFaturas;

    // Construtor da classe Faturas
    public Faturas() {
        this.listaFaturas = new ArrayList<>();
    }

    public void setListaFaturas(ArrayList<Fatura> novaListaFaturas) {
        if (novaListaFaturas != null) {
            this.listaFaturas = novaListaFaturas;
            System.out.println("Lista de faturas atualizada com sucesso.");
        } else {
            System.out.println("A nova lista de faturas é inválida (null).");
        }
    }


    // Método para ler os dados e adicionara fatura
    public void novaFatura() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número da fatura: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Consumir o "\n" pendente

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a data (dd/mm/aaaa): ");
        String dataStr = scanner.nextLine();

        // Converter a string de data para o formato Data
        String[] partes = dataStr.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        Data data = new Data(dia, mes, ano);

        System.out.print("Digite os produtos a adicionar: (separados por ','s) ");
        String produtosInput = scanner.nextLine();
        String[] produtosArray = produtosInput.split(",");

        // Criar a lista de produtos
        ArrayList<Produto> produtos = new ArrayList<>();
        for (int i = 0; i < produtosArray.length; i++) {
            //Produto produto = new Produto(produtosArray[i].trim());
            //produtos.add(produto);
        }

        adicionarFatura(numero, nome,data, produtos);
    }

    // Método para adicionar uma nova fatura à lista
    public void adicionarFatura(int numero, String nome, Data data, ArrayList<Produto> produtos) {
        Fatura novaFatura= new Fatura(numero, nome, data, produtos);
        listaFaturas.add(novaFatura);
        System.out.println("Nova fatura, " +numero+", adicionada com sucesso!");
    }

    public void listarFaturas() {
        if (listaFaturas.isEmpty()) {
            System.out.println("Nenhuma fatura cadastrada.");
        } else {
            for (Fatura fatura : listaFaturas) {
                System.out.println(fatura);
            }
        }
    }
}
