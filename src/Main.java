import java.util.Scanner;

public class Main {
    //as que faltam implementar:
    private void importarFaturas(){}
    private void exportarFaturas(){}
    private void apresentarEstatisticas(){}

    private Leituras leituras;
    private Clientes clientes;
    private Faturas faturas;

    //Construtor do main
    public Main() {
        this.clientes = new Clientes(); // Inicializa a lista de clientes
        this.faturas = new Faturas(clientes);  // Inicializa a lista de faturas
        this.leituras = new Leituras(clientes, faturas);
    }

    //nao percebi muito bem o porque mas devemos fazer o new main() correto??
    public static void main(String[] args) {
        Main programa = new Main(); // Cria a instância de Main
        programa.executar();

        //nao usei isto para nada, so nao apaguei porque podias querer ficar com ela, nao sei porque
        //receberLinha();
    }


    public void executar() {
        leituras.lerArquivo();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.print("MENU:\n1- Novo cliente\n2- Editar cliente\n3- Listar clientes\n4- Nova fatura\n5- Editar fatura\n6- Listar faturas\n7- Apresentar fatura\n"+
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
                    faturas.novaFatura();
                    break;
                case 5:
                    faturas.editarFatura();
                    break;
                case 6:
                    faturas.listarFaturas();
                    break;
                case 7:
                    //muito básica
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
            System.out.print("\nDeseja continuar? (S ou N): ");
            String continuar = scanner.next();
            if (continuar.equalsIgnoreCase("n")){
                System.out.println("Programa terminado.");
                return;
            }
        }
        scanner.close();
    }


    //nao usei isto para nada, so nao apaguei porque podias querer ficar com ela, nao sei porque
    //public static String receberLinha() {
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("> ");
        //return scanner.nextLine();
    //}
}
