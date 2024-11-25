import java.io.Serializable;

public class Data implements Serializable{
    private final int dia;
    private final int mes;
    private final int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}
