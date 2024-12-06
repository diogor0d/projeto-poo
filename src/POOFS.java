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
            System.out.printf("%s● Erro durante a manipulação dos ficheiros: %s", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.printf("%s┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n", Formatacao.YELLOW.getCode());
                System.out.printf("┃                  %s❯ Sistema de Gestão POOFS                     %s┃\n",Formatacao.BOLD.getCode(),Formatacao.YELLOW.getCode());
                System.out.printf("┃%s  %sMENU:                     "+Formatacao.GREEN.getCode()+"⠈⠛⠻⠶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    %s┃\n", Formatacao.YELLOW.getCode(), Formatacao.BOLD.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  1%s - Novo cliente           "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠈⢻⣆⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀   %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  2%s - Editar cliente         "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⢻⡏⠉⠉⠉⠉⢹⡏⠉⠉⠉⠉⣿⠉⠉⠉⠉⠉⣹⠇⠀⠀   %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  3%s - Listar clientes        "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠈⣿⣀⣀⣀⣀⣸⣧⣀⣀⣀⣀⣿⣄⣀⣀⣀⣠⡿⠀⠀⠀   %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  4%s - Nova fatura            "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⢸⡇⠀⠀⠀⠀⣿⠁⠀⠀⠀⣿⠃⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  5%s - Editar fatura          "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⣤⣤⣼⣧⣤⣤⣤⣤⣿⣤⣤⣤⣼⡏⠀⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  6%s - Listar faturas         "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠀⢸⡇⠀⠀⠀⠀⣿⠀⠀⢠⡿⠀⠀⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  7%s - Visualizar fatura      "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⠤⠼⠷⠤⠤⠤⠤⠿⠦⠤⠾⠃⠀⠀⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  8%s - Apresentar estatísticas"+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  9%s - Exportar faturas       "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⢾⣷⢶⣶⠶⠶⠶⠶⠶⠶⣶⠶⣶⡶⠀⠀⠀⠀⠀    %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  10%s- Importar faturas      "+Formatacao.GREEN.getCode()+"⠀⠀⠀⠀⠀⠀⠀⠀⠸⣧⣠⡿⠀⠀⠀⠀⠀⠀⢷⣄⣼⠇⠀⠀⠀⠀⠀     %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┃  0%s - Sair                                                      %s┃\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛%s\n", Formatacao.RESET.getCode());
                System.out.printf("%s%s❯ Selecione a operação → %s", Formatacao.BOLD.getCode(), Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());

                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 0 || opcao > 10) {
                    System.out.printf("%s● Opção inválida! Escreva um número de 0 a 10.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    continue;
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
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        System.out.printf("%s┏━━━━━━━━━━━━━━━━━━━━━━┓\n", Formatacao.YELLOW.getCode());
                        System.out.println("┃  Execução terminada  ┃");
                        System.out.printf("┗━━━━━━━━━━━━━━━━━━━━━━┛%s\n", Formatacao.RESET.getCode());
                        return;
                    default:
                        System.out.printf("%s● Opção inválida. Escreva um número de 0 a 10.%s", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                }

                while (true) {
                    System.out.printf("%s❯ Deseja regressar ao menu? (S/N) → %s", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
                    String continuar = scanner.nextLine();
                    if (continuar.trim().equalsIgnoreCase("N")) {
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        scanner.close();
                        System.out.printf("%s┏━━━━━━━━━━━━━━━━━━━━━━┓\n", Formatacao.YELLOW.getCode());
                        System.out.println("┃  Execução terminada  ┃");
                        System.out.printf("┗━━━━━━━━━━━━━━━━━━━━━━┛%s\n", Formatacao.RESET.getCode());
                        return; // Encerra o programa
                    } else if (continuar.trim().equalsIgnoreCase("S")) {
                        break;
                    } else {
                        System.out.printf("%s● Entrada inválida. Insira apenas %s'S'%s ou %s'N'%s.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    }
                }

            } catch (NumberFormatException e) {
                System.out.printf("%s● Entrada inválida! Digite um número válido.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            } catch (Exception e) {
                System.out.printf("%s● Erro inesperado: %s%s\n" , Formatacao.RED.getCode(),e.getMessage(), Formatacao.RESET.getCode());
            }
        }
    }
}
