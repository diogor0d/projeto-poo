import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Produto> produtos = new ArrayList<>(Arrays.asList(
                new ProdutoAlimentarTI(1234, "vinho de pacote", "750ml vinho de pacote biologico do douro", 2, 250.00, true, CategoriaAlimentar.VINHO),
                new ProdutoAlimentarTR(12366, "melao", "500g melao do panelas", 1, 5.00, true, new ArrayList<>(Arrays.asList(Certificacao.HACCP))),
                new ProdutoAlimentarTN(27311, "queijo", "750g queijo de cabra", 4, 10.00, false)
        ));

        Cliente cristinoRondo = new Cliente("Cristino Rondo", 23345123, "Madeira");
        Data dataFaturaExemplo = new Data(23,1,2065);

        Fatura debug = new Fatura(8347113, cristinoRondo, dataFaturaExemplo, produtos);
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
