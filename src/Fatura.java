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
        public void setCliente(Cliente cliente) { this.cliente = cliente; }
        public void setData(Data data) { this.data = data; }
        public void setProdutos(ArrayList<Produto> produtos) { this.produtos = produtos; }


        public String toString() {
            String output = "Fatura: " + num + " , CLiente" + cliente.getNome() + ", Contribuinte: " + cliente.getContribuinte() + ", Localização do cliente: " + cliente.getLocalizacao()+ ", Data: " + data + ", Produtos: ";
            boolean primeira_fatura = true;
            for(Produto produto: produtos){
                if(primeira_fatura){
                    output+= produto.getNome();
                    primeira_fatura = false;
                }
                else{
                    output+= ", " + produto.getNome();
                }

            }
            return output;
        }
    }
