// duvida: colocamos as linhas 12 a 14 como final??
// ve se queres apagar as linhas 27 e 28, e de 85 a 90 que eu nao usei esse metodo

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    //Construtor do main
    public Main() {
        this.clientes = new Clientes(); // Inicializa a lista de clientes
        this.faturas = new Faturas(clientes);  // Inicializa a lista de faturas
        this.leituras = new Leituras(clientes, faturas);
    }

    public static void main(String[] args) {

        ArrayList<Produto> produtos = new ArrayList<>(Arrays.asList(
                new ProdutoAlimentarTI(1234, "vinho de pacote", "750ml vinho de pacote biologico do douro", 2, 250.00, true, CategoriaAlimentar.VINHO),
                new ProdutoAlimentarTR(12366, "melao", "500g melao do panelas", 1, 5.00, true, new ArrayList<>(Arrays.asList(Certificacao.HACCP))),
                new ProdutoAlimentarTN(27311, "queijo", "750g queijo de cabra", 4, 10.00, false),
                new ProdutoFarmaciaNaoPrescrito(9281, "dorflex", "10 comprimidos dorflex", 2, 250.00, CategoriaFarmacia.ANALGESICO),
                new ProdutoFarmaciaPrescrito(9391, "dorflex", "10 comprimidos pastilhas", 2, 250.00, true, CategoriaFarmacia.ANALGESICO, "Dr. Joao")
        ));

        Cliente cristinoRondo = new Cliente("Cristino Rondo", 23345123, "Madeira");
        Data dataFaturaExemplo = new Data(23,1,2065);

        Fatura debug = new Fatura(8347113, cristinoRondo, dataFaturaExemplo, produtos);

        Main programa = new Main(); // Cria a instância de Main
        programa.executar();

    }



    private void importarFaturas(){}
    private void exportarFaturas(){}


    private Leituras leituras;
    private Clientes clientes;
    private Faturas faturas;

    public void executar() {
        leituras.lerArquivo();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.print("MENU:\n1- Novo cliente\n2- Editar cliente\n3- Listar clientes\n4- Nova fatura\n5- Editar fatura\n6- Listar faturas\n7- Apresentar fatura\n" +
                        "8- Apresentar estatísticas\n0- Sair\nOpcão-> ");
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 0 || opcao > 8) {
                    System.out.println("Opção inválida! Digite um número entre 0 e 8.");
                    continue; // Volta para a entrada de opção
                }

                switch (opcao) {
                    case 1:
                        clientes.novoCliente();
                        break;
                    case 2:
                        clientes.editarCliente();
                        break;
                    case 3:
                        clientes.listarClientes();
                        break;
                    case 4:
                        faturas.novaFatura();
                        break;
                    case 5:
                        faturas.editarFatura();
                        break;
                    case 6:
                        faturas.listarFaturas();
                        break;
                    case 7:
                        //muito inicial
                        faturas.vizualizarFatura();
                        break;
                    case 8:
                        apresentarEstatisticas();
                        break;
                    case 0:
                        System.out.println("Programa terminado.");
                        return;
                    default:
                        System.out.println("Opção inválida. Escreva um número de 0 a 8.");
                }
                while (true) {
                    System.out.print("\nDeseja continuar? (S ou N): ");
                    String continuar = scanner.nextLine();
                    if (continuar.equalsIgnoreCase("N")) {
                        System.out.println("Programa terminado.");
                        scanner.close();
                        return; // Encerra o programa
                    } else if (continuar.equalsIgnoreCase("S")) {
                        break;
                    } else {
                        System.out.println("Entrada inválida. Digite apenas 'S' ou 'N'.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

    }

    private void apresentarEstatisticas() {
    }
}
