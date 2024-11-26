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
                System.out.print("""
                        MENU:
                        1- Novo cliente
                        2- Editar cliente
                        3- Listar clientes
                        4- Nova fatura
                        5- Editar fatura
                        6- Listar faturas
                        7- Visualizar fatura
                        8- Apresentar estatísticas
                        9- Exportar faturas
                        10- Importar faturas
                        0- Sair
                        Selecione a operação >\s""");
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
                        System.out.println("Programa terminado.");
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        return;
                    default:
                        System.out.println("Opção inválida. Escreva um número de 0 a 8.");
                }
                while (true) {
                    System.out.print("\nDeseja voltar ao menu? (S ou N): ");
                    String continuar = scanner.nextLine();
                    if (continuar.equalsIgnoreCase("N")) {
                        System.out.println("Programa terminado.");
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
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
}
