import java.util.Scanner;

public class Main {
    private void novoCliente(){}
    private void editarCliente(){}
    private void listarClientes(){}
    private void criarFatura(){}
    private void editarFatura(){}
    private void listarFaturas(){}
    private void apresentarFatura(){}
    private void importarFaturas(){}
    private void exportarFaturas(){}
    private void apresentarEstatisticas(){}

    public static void main(String[] args) {
        Main programa = new Main(); // Cria a instância de Main
        programa.executar(); // Inicia a execução do programa
        receberLinha();
    }


    public void executar() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.print("MENU:\n1- Novo cliente\n2- Editar cliente\n3- Listar clientes\n4- Criar fatura\n5- Editar fatura\n6- Listar faturas\n7- Apresentar fatura\n"+
                            "8- Apresentar estatísticas\n0- Sair\nOpcão-> ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    novoCliente();
                    break;
                case 2:
                    editarCliente();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    criarFatura();
                    break;
                case 5:
                    editarFatura();
                    break;
                case 6:
                    listarFaturas();
                    break;
                case 7:
                    apresentarFatura();
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
            System.out.print("\n");
        }
        scanner.close();
    }



    public static String receberLinha() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
}
