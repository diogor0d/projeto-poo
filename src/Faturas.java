import java.util.ArrayList;
import java.util.Scanner;

public class Faturas{
    private ArrayList<Fatura> listaFaturas;

    // Construtor da classe Clientes
    public Faturas() {
        this.listaFaturas = new ArrayList<>();
    }

    public void setListaFaturas(ArrayList<Fatura> novaListaFaturas) {
        if (novaListaFaturas != null) {
            this.listaFaturas = novaListaFaturas;
            System.out.println("Lista de faturas atualizada com sucesso.");
        } else {
            System.out.println("A nova lista de faturas é inválida (null).");
        }
    }
}
