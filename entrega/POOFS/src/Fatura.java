//duvida: linha 32; (metodos getdata, getprodutos e setprodutos ainda nao tao a ser utilizados)

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe que representa uma fatura
 */
public class Fatura implements Serializable {
    /**
     * Número da fatura
     */
    private int num;

    /**
     * Cliente associado à fatura
     */
    private Cliente cliente;

    /**
     * Data da fatura
     */
    private Data data;

    /**
     * Lista de produtos da fatura
     */
    private ArrayList<Produto> produtos;

    /**
     * Construtor da classe Fatura
     *
     * @param num      Número da fatura
     * @param cliente  Cliente associado à fatura
     * @param data     Data da fatura
     * @param produtos Lista de produtos da fatura
     */
    public Fatura(int num, Cliente cliente, Data data, ArrayList<Produto> produtos) {
        this.num = num;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    /**
     * Método que calcula o total bruto da fatura
     *
     * @return Total bruto (sem impostos) da fatura
     */
    private double calcularTotalBruto() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco() * produto.getQuantidade();
        }

        return total;
    }

    // Getters e Setters

    /**
     * Método que devolve o número da fatura
     *
     * @return Número da fatura
     */
    public int getNum() {
        return num;
    }

    /**
     * Método que devolve o cliente associado à fatura
     *
     * @return Cliente associado à fatura
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Método que devolve a data da fatura
     *
     * @return Data da fatura
     */
    public Data getData() {
        return data;
    }

    /**
     * Método que devolve a lista de produtos da fatura
     *
     * @return Lista de produtos da fatura
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Método que define o número da fatura
     *
     * @param numero Número da fatura
     */
    public void setNumero(int numero) {
        this.num = numero;
    }

    /**
     * Método que define o cliente associado à fatura
     *
     * @param cliente Cliente associado à fatura
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Método que define a data da fatura
     *
     * @param data Data da fatura
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Método que define a lista de produtos da fatura
     *
     * @param produtos Lista de produtos da fatura
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Método que devolve a representação em string da fatura
     *
     * @return Fatura sob a forma de String
     */
    public String toString() {
        String[] produtosArray = new String[produtos.size()];
        for (int i = 0; i < produtos.size(); i++) {
            produtosArray[i] = produtos.get(i).getNome();
        }
        // Usar String.join para unir os nomes dos produtos com vírgulas
        String produtosConcatenados = String.join(", ", produtosArray);

        return String.format(
                "Nº: %-5s  Cliente: %-20s  Contribuinte: %-8s  Localização: %-13s  > Data: %-12s Produtos: %s",
                num, cliente.getNome(), cliente.getContribuinte(), cliente.getLocalizacao(), data, produtosConcatenados
        );
    }

    /**
     * Método que devolve a representação em string da fatura com sob o formato tabela de fatura
     *
     * @return String de fatura sob a forma de tabela
     */
    public String toStringFaturaFormatada() {
        String infoCabecalhoFatura = String.format(
                """
                        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                                                          Fatura simplificada nº  %-48d 
                                                          Cliente                 %-48s 
                                                          Nº Contribuinte         %-48d 
                                                          Data                    %-48s 
                                                          Região                  %-48s 
                        ━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━┳━━━━━━━━━━━┳━━━━━━━━━━━━━━
                        QNT┃ Produto                                   ┃  Preço    ┃  Taxa   ┃ Valor IVA ┃ Subtotal  ┃ Subt. + Taxas
                        ━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━╋━━━━━━━━━╋━━━━━━━━━━━╋━━━━━━━━━━━╋━━━━━━━━━━━━━━
                        """,
                num, cliente.getNome(), cliente.getContribuinte(), data, cliente.getLocalizacao()
        );

        double totalIva = 0;
        StringBuilder sb = new StringBuilder();
        for (Produto produto : getProdutos()) {
            double subtotal = produto.getPreco() * produto.getQuantidade();
            sb.append(String.format(" %-2d┃ %-42s┃ %-8.2f€ ┃ %-6.2f%% ┃ %-6.2f €  ┃ %-8.2f€ ┃  %-8.2f€   \n",
                    produto.getQuantidade(),
                    produto.getNome(),
                    produto.getPreco(),
                    produto.calcularIva(cliente) * 100,
                    produto.getPreco() * produto.calcularIva(cliente),
                    subtotal,
                    subtotal + (subtotal * produto.calcularIva(cliente))
            ));
            totalIva += subtotal + (subtotal * produto.calcularIva(cliente));
        }

        String totais = String.format(
                """
                        ━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                        %sTotal s/IVA: %.2f€ | Total IVA: %.2f€ | Total c/IVA: %.2f€%s
                        ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                        """,
                " ".repeat((108 - String.format("Total s/IVA: %.2f€ | Total IVA: %.2f€ | Total c/IVA: %.2f€", calcularTotalBruto(), totalIva - calcularTotalBruto(), totalIva).length()) / 2),
                calcularTotalBruto(),
                totalIva - calcularTotalBruto(),
                totalIva,
                " ".repeat((108 - String.format("Total s/IVA: %.2f€ | Total IVA: %.2f€ | Total c/IVA: %.2f€", calcularTotalBruto(), totalIva - calcularTotalBruto(), totalIva).length()) / 2)
        );

        return infoCabecalhoFatura + sb + totais;
    }


    /**
     * Método que devolve a representação em string da fatura para ser guardada em ficheiro
     *
     * @return Fatura sob a forma de String para guardar no ficheiro
     */
    public String toStringFicheiro() {
        String[] produtosArray = new String[produtos.size()];
        for (int i = 0; i < produtos.size(); i++) {
            produtosArray[i] = produtos.get(i).getNome();
        }
        // Usar String.join para unir os nomes dos produtos com vírgulas
        String produtosConcatenados = String.join(", ", produtosArray);

        return num + ", " + cliente.getContribuinte() + ", " + data + ", " + produtosConcatenados;
    }

}
