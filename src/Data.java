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

    // Metodo para verificar se uma Data é válida ou não
    public boolean isDataValida() {
        if (ano < 200 || ano > 2024) return false;

        if (mes < 1 || mes > 12) return false;

        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // anos bissextos
        if (mes == 2 && ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0))) {
            diasPorMes[1] = 29;
        }
        return dia >= 0 && dia <= diasPorMes[mes - 1];
    }
}
