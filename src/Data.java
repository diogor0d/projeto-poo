import java.io.Serializable;

/**
 * Classe que representa uma data
 */
public class Data implements Serializable {
    /**
     * Dia da data
     */
    private final int dia;

    /**
     * Mês da data
     */
    private final int mes;

    /**
     * Ano da data
     */
    private final int ano;

    /**
     * Construtor da classe Data
     *
     * @param dia Dia da data
     * @param mes Mês da data
     * @param ano Ano da data
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Método que devolve a representação em string da data
     *
     * @return Data em String
     */
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    /**
     * Método que verifica se a data é válida
     *
     * @return True se a data for válida, false caso contrário
     */
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
