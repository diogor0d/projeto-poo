public abstract class Produto {
    protected int codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double preco;

    // Construtor
    public Produto(int codigo, String nome, String descricao, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public abstract double calcularIva (Cliente cliente);

    public double calcularSubtotalProduto(Produto produto) {
        return produto.getPreco() * produto.getQuantidade();
    }

    // Getter e Setter

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // // toString
    public String toString() {
        return "codigo= " + codigo + ", nome= " + nome + '\'' + ", descricao= " + descricao + '\'' + ", quantidade= " + quantidade + ", preco= " + preco;
    }
}
