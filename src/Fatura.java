    import java.util.ArrayList;

    class Fatura {
        private int num;
        private Cliente cliente;
        private Data data;
        private ArrayList<Produto> produtos;

        public Fatura(int num, Cliente cliente, Data data, ArrayList<Produto> produtos) {
            this.num = num;
            this.cliente = cliente;
            this.data = data;
            this.produtos = produtos;
        }

        // Getters e Setters
        public int getNum() { return num; }
        public Cliente getCliente() { return cliente; }
        public Data getData() { return data; }
        public  ArrayList<Produto> getProdutos() { return produtos; }

        public void setNumero(int numero) { this.num = numero; }
        public void setNomeCliente(Cliente cliente) { this.cliente = cliente; }
        public void setData(Data data) { this.data = data; }
        public void setProdutos(ArrayList<Produto> produtos) { this.produtos = produtos; }


        public String toString() {
            String output = "Fatura: " + num + ", Contribuinte: " + cliente.getContribuinte() + ", Data: " + data + ", Produtos: ";
            for(Produto produto: produtos){
                output+= produto.getNome()+ ", ";
            }
            return output;
        }
    }
