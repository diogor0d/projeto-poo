class Cliente {
    private String nome;
    private int contribuinte;
    private String localizacao;

    public Cliente(String nome, int contribuinte, String localizacao) {
        this.nome = nome;
        this.contribuinte = contribuinte;
        this.localizacao = localizacao;
    }

    public String getNome() {
        return nome;
    }

    public int getContribuinte() {
        return contribuinte;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContribuinte(int contribuinte) {
        this.contribuinte = contribuinte;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String toString() {
        return "Nome: " + nome + ", Contribuinte: " + contribuinte + ", Localização: " + localizacao;
    }
}
