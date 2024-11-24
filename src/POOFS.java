import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class POOFS {
    private final Leituras leituras;
    private final Clientes clientes;
    private final Faturas faturas;
    private final Produtos produtos;

    public POOFS() {
        this.clientes = new Clientes(); // Inicializa a lista de clientes
        this.produtos = new Produtos();
        this.faturas = new Faturas(clientes, produtos);  // Inicializa a lista de faturas
        this.leituras = new Leituras(clientes, faturas, produtos);
    }

    public static void main(String[] args) {
        POOFS sistema = new POOFS(); // Cria a instância de Main
        sistema.iniciar();
    }

    private void importarFaturas() {
    }

    public void iniciar() {
        try {
            leituras.lerFicheiro();
            //mais tarde usaremos o ficheiro de input para as duas coisas
            leituras.exportarFaturas(faturas.getFaturas());
        } catch (Exception e) {
            System.out.println("Erro durante a leitura dos ficheiros: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.print("""
                        MENU:
                        1- Novo cliente
                        2- Editar cliente
                        3- Listar clientes
                        4- Nova fatura
                        5- Editar fatura
                        6- Listar faturas
                        7- Apresentar fatura
                        8- Apresentar estatísticas
                        9- Exportar faturas
                        10- Importar faturas
                        0- Sair
                        Opcão->\s""");
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
                        apresentarEstatisticas();
                        break;
                    case 9:
                        leituras.exportarFaturas(faturas.getFaturas());
                        break;
                    case 10:
                        leituras.importarFaturas();
                        break;
                    case 0:
                        System.out.println("Programa terminado.");
                        leituras.exportarFaturas(faturas.getFaturas());
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
                System.out.println("Entrada inválida! Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

    }

    private void apresentarEstatisticas() {
    }
}
