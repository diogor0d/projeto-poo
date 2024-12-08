import java.io.Serializable;

/**
 * Classe que representa um cliente
 */
public class Cliente implements Serializable {
    /**
     * Nome do cliente
     */
    private String nome;

    /**
     * Número de contribuinte do cliente
     */
    private int contribuinte;

    /**
     * Localização do cliente
     */
    private String localizacao;

    /**
     * Construtor da classe Cliente
     *
     * @param nome         Nome do cliente
     * @param contribuinte Número de contribuinte do cliente
     * @param localizacao  Localização do cliente
     */
    public Cliente(String nome, int contribuinte, String localizacao) {
        this.nome = nome;
        this.contribuinte = contribuinte;
        this.localizacao = localizacao;
    }


    // Getters e Setters

    /**
     * Método que devolve o nome do cliente
     *
     * @return Nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que devolve o número de contribuinte do cliente
     *
     * @return Número de contribuinte do cliente
     */
    public int getContribuinte() {
        return contribuinte;
    }

    /**
     * Método que devolve a localização do cliente
     *
     * @return Localização do cliente
     */
    public String getLocalizacao() {
        return localizacao;
    }


    /**
     * Método que define o nome do cliente
     *
     * @param nome Nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que define o número de contribuinte do cliente
     *
     * @param contribuinte Número de contribuinte do cliente
     */
    public void setContribuinte(int contribuinte) {
        this.contribuinte = contribuinte;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Método que devolve a representação em String do cliente
     *
     * @return Representação em String do cliente
     */
    public String toString() {
        return String.format("  Nome: %-20s  Contribuinte: %-15s  Localização: %-15s ",
                nome, contribuinte, localizacao);
    }
}
