package mvvm.sistemadeprodutos.viewmodel;

import java.util.ArrayList;
import java.util.List;

import mvvm.sistemadeprodutos.model.Produto;
import mvvm.sistemadeprodutos.service.ProdutoService;

public class ProdutoViewModel extends Observable{

    private ProdutoService service;
    private List<Produto> produtos;
    private Produto produtoSelecionado;
    private String mensagem;
    private String nome;
    private String preco;
    private String quantidade;

    private Command carregarProdutoCommand;
    private Command adicionarProdutoCommand;
    private Command atualizarProdutCommand;
    private Command excluiProdutoCommand;
    private CommandComParametro <Long> selecionarProdutoCommand;

    public static final String PROP_PRODUTOS = "produtos";
    public static final String PROP_PRODUTO_SELECIONADO = "produtoSelecionado";
    public static final String PROP_MENSAGEM = "mensagem";
    public static final String PROP_NOME = "nome";
    public static final String PROP_PRECO = "preco";
    public static final String PROP_QUANTIDADE = "quantidade";

    public ProdutoViewModel(ProdutoService service){
        this.service = service;
        this.produtos = new ArrayList<>();
        inicializarCommands();
        carregarProdutos();
    
    }

    public void inicializarCommands(){
        this.carregarProdutoCommand = () -> carregarProdutos();
        this.adicionarProdutoCommand = () -> adicionarProduto();
        this.atualizarProdutCommand = () -> atualizarProduto();
        this.excluiProdutoCommand = () -> excluirProduto();
        this.selecionarProdutoCommand = (id) -> selecionarProduto(id);
    
    }


    public List<Produto> getProdutos(){
        return produtos;
    }

    public void setProdutos(List<Produto> produtos){
        List<Produto> listaVelha = this.produtos;
        this.produtos = produtos != null ? new ArrayList<>(produtos) : new ArrayList<>();
        notificarObservador(PROP_PRODUTOS, listaVelha, this.produtos);

    }

    public Produto getProdutoSelecionado(){
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado){
        Produto produtoVelho = this.produtoSelecionado;
        this.produtoSelecionado = produtoSelecionado;
        notificarObservador(PROP_PRODUTO_SELECIONADO, produtoVelho, this.produtoSelecionado);

       if (produtoSelecionado != null) {
        setNome(produtoSelecionado.getNome());
        setPreco(produtoSelecionado.getPreco());
        setQuantidade(produtoSelecionado.getQuantidade());
       }else{
         limparCampos();
    
       }

    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem){
        String mensagemVelha = this.mensagem;
        this.mensagem = mensagem;
        notificarObservador(PROP_MENSAGEM, mensagemVelha, this.mensagem);
    
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        String nomeVelho = this.nome;
        this.nome = nome;
        notificarObservador(PROP_NOME, nomeVelho, this.nome);
    }

    public String getPreco(){
        return preco;
    }

    public void setPreco(String preco){
        String precoVelho = this.preco;
        this.preco = preco;
        notificarObservador(PROP_PRECO, precoVelho, this.preco);
        
    }

    public String getQuantidade(){
        return quantidade;
        
    }

    public void setQuantidade(String quantidade){
     String quantidadeVelha = this.quantidade;
        this.quantidade = quantidade;
        notificarObservador(PROP_QUANTIDADE, quantidadeVelha, this.quantidade);
    
    }

    
    public Command getCarregarProdutoCommand(){
        return carregarProdutoCommand;
    
    }

    public Command getAdicionarProdutoCommand(){
        return adicionarProdutoCommand;
    }

    public Command getAtualizarProdutCommand(){
        return atualizarProdutCommand;
    }
   
    public Command getExcluiProdutoCommand(){
        return excluiProdutoCommand;
    }
   

    public CommandComParametro<Long> getSelecionarProdutoCommand(){
        return selecionarProdutoCommand;
    }

    public void carregarProdutos(){
        setProdutos(service.listarProdutos());
        setMensagem("Produtos Carregados: " + produtos.size());
    }

    public void adicionarProduto(){
        if (!validarDados()) {
           return;
        }

        Produto produto = new Produto( null, nome.trim(), preco != null ? preco.trim() : "", quantidade != null ? quantidade.trim() : ""  );
        if (service.salvar(produto)) {
            setMensagem("Produto adicionado com sucesso. ");
            limparCampos();
            carregarProdutos();
        }else{
            setMensagem("Erro ao adicionar produto");
        }
    }

    public void atualizarProduto(){
        if (produtoSelecionado == null) {
        setMensagem("Selecione um produto para atiualizar");      
        }
        if (!validarDados()) {
            return;
        }

        produtoSelecionado.setNome(nome.trim());
        produtoSelecionado.setPreco(preco != null ? preco.trim(): "");
        produtoSelecionado.setQuantidade(quantidade != null ? quantidade.trim(): "");
        if (service.atualizar(produtoSelecionado)) {
            setMensagem("Produto atualizado");
            limparCampos();
            carregarProdutos();
            
        }else{
            setMensagem("Erro ao atualizar");
        }
    }

    public void excluirProduto(){
        if (produtoSelecionado == null) {
            setMensagem("Selecione um produto para excluir ");
            return;
            
        }

        if (service.excluir(produtoSelecionado.getId())) {
            setMensagem("Produto excluído");
            limparCampos();
            carregarProdutos();
        }else{
            setMensagem("Erro ao excluir");
        }


    }

    public void selecionarProduto(Long id){
        Produto produto = service.buscarPorId(id);
        setProdutoSelecionado(produto);
           
    }

    public void limparCampos(){
        setNome("");
        setPreco("");
        setQuantidade("");
       

    }

    public boolean validarDados(){
        if (nome == null || nome.trim().isEmpty() || preco == null || preco.trim().isEmpty() || quantidade == null || quantidade.trim().isEmpty()  ) {
            setMensagem("nome, preço e quantidade são obrigatórios");
            return false;
        
        }
        return true;

        

    }


}

