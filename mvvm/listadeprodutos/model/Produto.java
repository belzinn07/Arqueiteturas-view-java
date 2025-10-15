package mvvm.listadeprodutos.model;

public class Produto {
    private Long id;
    private String nome;
    private String preco;
    private String quantidade;
    


    public Produto(Long id,String nome, String preco, String quantidade){
           
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
        
    }

    public Produto(String trim, String string, boolean b) {
        //TODO Auto-generated constructor stub
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }



    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }


    public String getPreco(){
        return preco;
    }
    public void setPreco(String preco){
        this.preco = preco;
    }


    public String getQuantidade(){
        return quantidade;
    }
    public void setQuantidade(String quantidade){
        this.quantidade = quantidade;
    }


   

    @Override
    public String toString(){
        return id + " - " + nome + " Preço: R$  " + preco + " Qtd: " + quantidade ;
    }

    
}
