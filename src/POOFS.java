import java.util.Scanner;

public class POOFS {
    // Declaração das classes que manipulam os clientes, faturas e produtos e da que é responsável pelas leituras e escritas dos arquivos
    private final Clientes clientes;
    private final Faturas faturas;
    private final Produtos produtos;
    private final Leituras leituras;

    public POOFS() {
        // Inicializa dessas classes, criando uma nova instância para cada uma
        this.clientes = new Clientes();
        this.produtos = new Produtos();
        this.faturas = new Faturas(clientes, produtos);
        this.leituras = new Leituras(clientes, faturas, produtos);
    }

    public static void main(String[] args) {
        // Cria a instância de Main, ou seja inicializar o sistema
        POOFS sistema = new POOFS();
        sistema.iniciar();
    }

    public void iniciar() {
        try {
            leituras.lerFicheiro();
            //mais tarde usaremos o ficheiro de input para as duas coisas
            leituras.exportarFaturas(faturas.getListaFaturas());
        } catch (Exception e) {
            System.out.println("Erro durante a leitura dos ficheiros: " + e.getMessage());
        }

        Scanner scanner = new   Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.println("--------------------------------------------------");
                System.out.println(String.format("|  %-46s |", "MENU:"));
                System.out.println(String.format("|  %-46s |", "1- Novo cliente"));
                System.out.println(String.format("|  %-46s |", "2- Editar cliente"));
                System.out.println(String.format("|  %-46s |", "3- Listar clientes"));
                System.out.println(String.format("|  %-46s |", "4- Nova fatura"));
                System.out.println(String.format("|  %-46s |", "5- Editar fatura"));
                System.out.println(String.format("|  %-46s |", "6- Listar faturas"));
                System.out.println(String.format("|  %-46s |", "7- Visualizar fatura"));
                System.out.println(String.format("|  %-46s |", "8- Apresentar estatísticas"));
                System.out.println(String.format("|  %-46s |", "9- Exportar faturas"));
                System.out.println(String.format("|  %-46s |", "10- Importar faturas"));
                System.out.println(String.format("|  %-46s |", "0- Sair"));
                System.out.println("--------------------------------------------------");
                System.out.print("Selecione a operação > ");
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 0 || opcao > 10) {
                    System.out.println("Opção inválida! Escreva um número de 0 a 10."); //so para testar
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
                        faturas.visualizarFatura();
                        break;
                    case 8:
                        faturas.apresentarEstatisticas();
                        break;
                    case 9:
                        //Em vez de receber argumento, exporta as faturas na class Faturas e ta feito
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        break;
                    case 10:
                        leituras.importarFaturas();
                        break;
                    case 0:
                        System.out.println("------------------------");
                        System.out.println("|  Programa terminado  |");
                        System.out.println("------------------------");
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        return;
                    default:
                        System.out.println("Opção inválida. Escreva um número de 0 a 8.");
                }
                while (true) {
                    System.out.print("\nDeseja voltar ao menu? (S ou N): ");
                    String continuar = scanner.nextLine();
                    if (continuar.trim().equalsIgnoreCase("N")) {
                        System.out.println("------------------------");
                        System.out.println("|  Programa terminado  |");
                        System.out.println("------------------------");
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        scanner.close();
                        return; // Encerra o programa
                    } else if (continuar.trim().equalsIgnoreCase("S")) {
                        break;
                    } else {
                        System.out.println("Entrada inválida. Digite apenas 'S' ou 'N'.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
