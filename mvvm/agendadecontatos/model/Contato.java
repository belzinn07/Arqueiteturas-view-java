public class Contato{
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public Contato( String nome, String telefone, String email){
       this.nome = nome;
       this.telefone= telefone;
       this.email= email;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
       this.id = id;
    }

    public void setNome( String nome){
        thi.nome=nome;
    }
}
