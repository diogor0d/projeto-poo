/**
 * Enum com os códigos unicode para a formatação de texto no terminal.
 *
 */
public enum Formatacao {
    BOLD("\u001B[1m"),
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    /**
     * Código unicode da formatação.
     */
    private final String code;

    /**
     * Construtor do enum Formatacao.
     *
     * @param code Titulo do código unicode da formatação.
     */
    Formatacao(String code) {
        this.code = code;
    }

    /**
     * Método que devolve o código unicode da formatação.
     *
     * @return Código unicode da formatação.
     */
    public String getCode() {
        return code;
    }
}