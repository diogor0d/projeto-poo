import java.util.ArrayList;
import java.util.Scanner;

public class Faturas{
    private ArrayList<Fatura> listaFaturas;
    private Clientes clientes;

    // Construtor da classe Faturas
    public Faturas(Clientes clientes) {
        this.clientes = clientes;
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

        System.out.print("Digite o contriubuinte do cliente: ");
        int contribuinte = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = clientes.procurarClientePorContribuinte(contribuinte);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite a data (dd/mm/aaaa): ");
        String dataStr = scanner.nextLine();

        // Converter a string de data para o formato Data
        String[] partes = dataStr.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        Data data = new Data(dia, mes, ano);

        System.out.print("Digite os produtos a adicionar (separados por ','): ");
        String[] produtosArray = scanner.nextLine().split(",");
        System.out.println("Digite 'fim' para encerrar.");
        ArrayList<Produto> produtos = new ArrayList<>();

        while (true) {
            String linha = scanner.nextLine();
            if (linha.equalsIgnoreCase("fim")) break;

            String[] atributos = linha.split(",");
            if (atributos.length == 5) { // Verifica se há 5 campos
                try {
                    int codigo = Integer.parseInt(atributos[0].trim());
                    String nome = atributos[1].trim();
                    String descricao = atributos[2].trim();
                    int quantidade = Integer.parseInt(atributos[3].trim());
                    double preco = Double.parseDouble(atributos[4].trim());

                    Produto produto = new Produto(codigo, nome, descricao, quantidade, preco);
                    produtos.add(produto);
                    scanner.close();
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao processar produto: " + linha);
                }
            } else {
                System.out.println("Erro: Linha com formato inválido. Esperado: codigo, nome, descricao, quantidade, preco");
            }
        }
        adicionarFatura(numero, cliente, data, produtos);
    }


    // Método para adicionar uma nova fatura à lista
    public void adicionarFatura(int numero, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        Fatura novaFatura= new Fatura(numero, cliente, data, produtos);
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
