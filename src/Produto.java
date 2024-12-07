import java.io.Serializable;

/**
 * Classe que representa um produto
 */
public abstract class Produto implements Serializable {
    /**
     * Código do produto
     */
    protected int codigo;
    /**
     * Nome do produto
     */
    protected String nome;
    /**
     * Descrição do produto
     */
    protected String descricao;
    /**
     * Quantidade do produto
     */
    protected int quantidade;
    /**
     * Preço do produto
     */
    protected double preco;

    /**
     * Construtor da classe Produto
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param descricao Descrição do produto
     * @param quantidade Quantidade do produto
     * @param preco Preço do produto
     */
    public Produto(int codigo, String nome, String descricao, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    /**
     * Método que calcula o IVA de um produto
     * @param cliente Cliente que está a comprar o produto, cuja localização influencia o valor do impossto a aplicar
     * @return Valor da Taxa do IVA do produto
     */
    public abstract double calcularIva (Cliente cliente);

    /**
     * Método que calcula o subtotal bruto de um produto (sem impostos)
     * @return Total subbruto do produto
     */
    public double calcularSubtotalProduto(Produto produto) {
        return produto.getPreco() * produto.getQuantidade();
    }

    // Getter e Setter

    /**
     * Método que devolve o código do produto
     * @return Código do produto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Método que devolve o nome do produto
     * @return Nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que devolve a descrição do produto
     * @return Descrição do produto
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método que devolve a quantidade do produto
     * @return Quantidade do produto
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Método que devolve o preço do produto
     * @return Preço do produto
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Método que define o código do produto
     * @param codigo Código do produto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Método que define o nome do produto
     * @param nome Nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que define a descrição do produto
     * @param descricao Descrição do produto
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Método que define a quantidade do produto
     * @param quantidade Quantidade do produto
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Método que define o preço do produto
     * @param preco Preço do produto
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Método que devolve a representação textual de um produto
     * @return Representação textual de um produto
     */
    public String toString() {
        return String.format(
                "%sCOD:%s%-7s %sNOME%s:%-20s %sDESC%s:%-40s %sQNT%s:%-4d %sP.UNI%s:%-7.2f€",Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode(),
                codigo,Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode(), "'"+nome+"'",Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode(),"'"+descricao+"'",Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode(),quantidade,Formatacao.YELLOW.getCode(), Formatacao.RESET.getCode(),preco
        );
    }
}
