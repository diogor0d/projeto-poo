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

    private Clientes clientes;
    //Construtor do main
    public Main() {
        this.clientes = new Clientes(); // Inicializa a classe Clientes
    }

    //nao percebi muito bem o porque mas devemos fazer o new main() correto??
    public static void main(String[] args) {
        Main programa = new Main(); // Cria a instância de Main
        programa.executar();
        //receberLinha();
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
                    clientes.novoCliente();
                    break;
                case 2:
                    clientes.editarCliente();
                    break;
                case 3:
                    clientes.listarClientes();
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
            System.out.print("\nDeseja continuar? (S ou N): ");
            String continuar = scanner.next();
            if (continuar.equalsIgnoreCase("n")){
                System.out.println("Programa terminado.");
                return;
            }
        }
        scanner.close();
    }



    //public static String receberLinha() {
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("> ");
        //return scanner.nextLine();
    //}
}
