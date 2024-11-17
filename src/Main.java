import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        receberLinha();
    }

    private void novoCliente(){}
    private void editarCliente(){}
    private void listarClientes(){}
    private void criarFatura(){}
    private void editarFatura(){}
    private void listarFaturas(){}
    private void apresentarFatura(){}
    private void importarFaturas(){}
    private void exportarFaturas(){}

    public static String receberLinha() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    // estatisticas ? ver depois
}
