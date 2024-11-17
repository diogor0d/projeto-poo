class Produto {
    protected int codigo;
    protected String nome;
    protected String descricao;
    protected int quantidade;
    protected double preco;

    public Produto(int codigo, String nome, String descricao, int quantidade, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
