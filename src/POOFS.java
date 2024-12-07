import java.util.ArrayList;
import java.util.Scanner;

public class POOFS {
    // Declaração das classes que manipulam os clientes, faturas e produtos e da que é responsável pelas leituras e escritas dos ficheiros
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

    private void iniciar() {
        try {
            leituras.lerFicheiro();
        } catch (Exception e) {
            System.out.printf("%s● Erro durante a manipulação dos ficheiros: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        while (true) {
            try {
                System.out.printf("\n                  %s❯ Sistema de Gestão POOFS                     %s\n", Formatacao.BOLD.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("%s  %sMENU:                     " + Formatacao.GREEN.getCode() + "⠈⠛⠻⠶⣶⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    %s\n", Formatacao.YELLOW.getCode(), Formatacao.BOLD.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  1%s - Novo cliente           " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠈⢻⣆⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀   %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  2%s - Editar cliente         " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⢻⡏⠉⠉⠉⠉⢹⡏⠉⠉⠉⠉⣿⠉⠉⠉⠉⠉⣹⠇⠀⠀   %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  3%s - Listar clientes        " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠈⣿⣀⣀⣀⣀⣸⣧⣀⣀⣀⣀⣿⣄⣀⣀⣀⣠⡿⠀⠀⠀   %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  4%s - Nova fatura            " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠸⣧⠀⠀⠀⢸⡇⠀⠀⠀⠀⣿⠁⠀⠀⠀⣿⠃⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  5%s - Editar fatura          " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⢹⣧⣤⣤⣼⣧⣤⣤⣤⣤⣿⣤⣤⣤⣼⡏⠀⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  6%s - Listar faturas         " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⠀⠀⢸⡇⠀⠀⠀⠀⣿⠀⠀⢠⡿⠀⠀⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  7%s - Visualizar fatura      " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⠤⠼⠷⠤⠤⠤⠤⠿⠦⠤⠾⠃⠀⠀⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  8%s - Apresentar estatísticas" + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  9%s - Exportar faturas       " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⢾⣷⢶⣶⠶⠶⠶⠶⠶⠶⣶⠶⣶⡶⠀⠀⠀⠀⠀    %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  10%s- Importar faturas      " + Formatacao.GREEN.getCode() + "⠀⠀⠀⠀⠀⠀⠀⠀⠸⣧⣠⡿⠀⠀⠀⠀⠀⠀⢷⣄⣼⠇⠀⠀⠀⠀⠀     %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("  0%s - Sair                                                      %s\n", Formatacao.RESET.getCode(), Formatacao.YELLOW.getCode());
                System.out.printf("\n%s%s❯ Selecione a operação → %s", Formatacao.BOLD.getCode(), Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());

                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 0 || opcao > 10) {
                    System.out.printf("%s● Opção inválida! Escreva um número de 0 a 10.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    continue;
                }

                switch (opcao) {
                    case 1 -> clientes.novoCliente();
                    case 2 -> clientes.editarCliente();
                    case 3 -> clientes.listarClientes();
                    case 4 -> faturas.novaFatura();
                    case 5 -> faturas.editarFatura();
                    case 6 -> faturas.listarFaturas();
                    case 7 -> faturas.visualizarFatura();
                    case 8 -> faturas.apresentarEstatisticas();
                    case 9 -> leituras.exportarFaturas(faturas.getListaFaturas());
                    case 10 -> leituras.importarFaturas();
                    case 0 -> {
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        System.out.println("  Execução terminada  ");
                        return;
                    }
                    default ->
                            System.out.printf("%s● Opção inválida. Escreva um número de 0 a 10.%s", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                }

                while (true) {
                    System.out.printf("%s❯ Deseja regressar ao menu? (S/N) → %s", Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode());
                    String continuar = scanner.nextLine();
                    if (continuar.trim().equalsIgnoreCase("N")) {
                        leituras.exportarFaturas(faturas.getListaFaturas());
                        leituras.escreverObjeto(faturas.getListaFaturas(), produtos.getListaProdutos(), clientes.getListaClientes());
                        scanner.close();
                        System.out.println("  Execução terminada  ");
                        return; // Encerra o programa
                    } else if (continuar.trim().equalsIgnoreCase("S")) {
                        break;
                    } else {
                        System.out.printf("%s● Entrada inválida. Insira apenas %s'S'%s ou %s'N'%s.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode(), Formatacao.RED.getCode(), Formatacao.RESET.getCode());
                    }
                }

            } catch (NumberFormatException e) {
                System.out.printf("%s● Entrada inválida! Introduza um número válido.%s\n", Formatacao.RED.getCode(), Formatacao.RESET.getCode());
            } catch (Exception e) {
                System.out.printf("%s● Erro inesperado: %s%s\n", Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode());
            }
        }
    }

    public static Object receberInput(Scanner scanner, CategoriaInput tipo, String mensagem) {
        switch (tipo) {
            case inteiro -> {
                int num = -1;
                boolean valido = false;
                while (!valido) {
                    try {
                        System.out.print(mensagem);
                        num = Integer.parseInt(scanner.nextLine());
                        valido = true;
                    } catch (NumberFormatException e) {
                        System.out.println("%s● Entrada inválida. Por favor, introduza um número válido. Introduza '-1' para sair.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    }
                }
                return num;
            }
            case data -> {
                while (true) {
                    try {
                        System.out.print(mensagem);
                        String dataStr = scanner.nextLine();

                        if (dataStr.equalsIgnoreCase("Cancelar")) {
                            return null;
                        }

                        String[] partes = dataStr.split("/");
                        if (partes.length != 3) {
                            throw new IllegalArgumentException("%s● Formato inválido. Siga o formato (DD/MM/AAAA). Use 'Cancelar' para sair.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        }

                        int dia = Integer.parseInt(partes[0].trim());
                        int mes = Integer.parseInt(partes[1].trim());
                        int ano = Integer.parseInt(partes[2].trim());

                        Data data = new Data(dia, mes, ano);
                        if (data.isDataValida()) {
                            return data;
                        } else {
                            throw new IllegalArgumentException("Data inválida. Verifique a validez da data introduzida. Use 'Cancelar' para sair.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("%s● Erro ao processar a data. Certifique-se que usa apenas números.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            case nome -> {
                String nome;
                while (true) {
                    try {
                        System.out.print(mensagem);
                        nome = scanner.nextLine();
                        nome = nome.trim();
                        if (isTextoValido(nome)) {
                            return nome;
                        } else {
                            System.out.println("%s● Nome inválido. Apenas letras e espaços são permitidos.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));

                            return null;
                        }
                    } catch (Exception e) {
                        System.out.println("%s● Erro ao processar o nome: %s".formatted(Formatacao.RED.getCode(), e.getMessage(), Formatacao.RESET.getCode()));
                    }
                }
            }
            case localizacao -> {
                while (true) {
                    System.out.print(mensagem);
                    try {
                        String localizacao;
                        localizacao = scanner.nextLine();
                        localizacao = localizacao.trim();
                        if (POOFS.isTextoValido(localizacao)) {
                            if (POOFS.isLocalizacaoValida(localizacao)) {
                                //Para  a primeira letra de cada palavra ser maiúscula
                                if (localizacao.equalsIgnoreCase("madeira")) {
                                    localizacao = "Madeira";
                                } else if (localizacao.equalsIgnoreCase("açores")) {
                                    localizacao = "Açores";
                                } else if (localizacao.equalsIgnoreCase("continente")) {
                                    localizacao = "Continente";
                                }
                                break;
                            } else {
                                System.out.print("%s● Localização não reconhecida! Introduza 'Continente', 'Açores' ou 'Madeira'. %s\n".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                            }
                        } else {
                            System.out.println("%s● Localização inválida. Apenas letras e espaços são permitidos.%s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()));
                        }
                    } catch (Exception e) {
                        System.out.println("%s● Erro ao processar a localização: %s".formatted(Formatacao.RED.getCode(), Formatacao.RESET.getCode()) + e.getMessage());
                    }
                }
            }
        }
        return null;
    }

    // Metodo para determinar se uma string é constituida apenas por caracteres e espaços
    public static boolean isTextoValido(String texto) {
        if (!texto.isEmpty()) {
            texto = String.join(" ", texto.split("\\s+"));
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                if (!(Character.isLetter(c) || c == ' ')) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


    public static boolean isLocalizacaoValida(String localizacao) {
        ArrayList<String> localizacoes = new ArrayList<>();
        localizacoes.add("continente");
        localizacoes.add("açores");
        localizacoes.add("madeira");

        for (String loc : localizacoes) {
            if (loc.equalsIgnoreCase(localizacao)) {
                return true;
            }
        }
        return false;
    }
}
