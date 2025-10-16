package mvvm.listadeprodutos.view;

import java.util.List;
import java.util.Scanner;

import mvvm.listadeprodutos.binding.DataBinder;
import mvvm.listadeprodutos.model.Produto;
import mvvm.listadeprodutos.viewmodel.ProdutoViewModel;

public class ProdutoView {
    private ProdutoViewModel viewModel;
    private DataBinder binder;
    private Scanner scanner;
    private boolean executando;

    public ProdutoView(ProdutoViewModel produtoViewModel){
        this.viewModel = viewModel;
        this.binder = new DataBinder();
        this.scanner = new Scanner(System.in);
        configuraDataBinding();
    }

    private void configuraDataBinding(){
        binder.ligar(viewModel, ProdutoViewModel.PROP_PRODUTOS, this::atualizarListaProdutos);
        binder.ligar(viewModel, ProdutoViewModel.PROP_MENSAGEM,this::exibirMensagem);
        binder.ligar(viewModel, ProdutoViewModel.PROP_NOME, this::atualizarNome );
        binder.ligar(viewModel, ProdutoViewModel.PROP_PRECO, this::atualizarPreco);
        binder.ligar(viewModel, ProdutoViewModel.PROP_QUANTIDADE, this::atualizarQuantidade);

        binder.ligarCommand(this, "carregarProdutos", viewModel.carregarProdutoCommand());
        binder.ligarCommand(this, "adicionarProdutos", viewModel.adicionarProdutoCommand());
        binder.ligarCommand(this, "atualizarProdutos", viewModel.atualizarProdutCommand());
        binder.ligarCommand(this, "excluirProduto", viewModel.excluiProdutoCommand());

    }

    public void iniciar(){
        executando =true;
        System.out.println("=======SISTEMA DE PRODUTOS=======");

        while (executando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            processarOpcao(opcao);

        }
    }

    public void exibirMenu(){
        System.out.println("======MENU PRODUTOS======");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Adicionar Produto");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Excluir Produto");
        System.out.println("5. Voltar ao Menu Principal");
        System.out.println("6. Escolha uma opção: ");

    }

    public void processarOpcao(int opcao){
        switch (opcao) {
            case 1:
                viewModel.getCarregarProdutoCommand().executar();
                break;
            
            case 2:
               lerDadosProduto();
               viewModel.getAdicionarProdutoCommand().executar();
               break;
            
            case 3:
               selecionarProdutoParaEdicao();
               viewModel.getAtualizarProdutCommand().executar();
               break;
               
            case 4:
               selecionarProdutoParaEdicao();
               viewModel.getExcluiProdutoCommand().executar();
               break;
               
            case 5:
               executando = false;
               System.out.println("Voltando ao menu principal...");
               break;   
                
            default:
               System.out.println("Opção inválida");
        }
    }

    public void lerDadosProduto(){
        scanner.nextLine();

        System.out.println("Nome do Produto: ");
        viewModel.setNome(scanner.nextLine());

        System.out.println("Preço: ");
        viewModel.setPreco(scanner.nextLine());

        System.out.println("Quantidade: ");
        viewModel.setQuantidade(scanner.nextLine());
    }

    public void selecionarProdutoParaEdicao(){
        List<Produto> produtos = viewModel.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado. ");
            return;
            
        }

        System.out.println("\n---Selecione um Produto---");
        for(int i = 0; i < produtos.size() ; i++){
            System.out.println((i + 1) + ". " + produtos.get(i).getNome() + " Preço: R$" + produtos.get(i).getPreco() + " Qtd: " + produtos.get(i).getQuantidade());
        }

        System.out.println("Selecione o número do produto: ");
        int selecao = scanner.nextInt();  

        if (selecao > 0 && selecao <= produtos.size()) {
            viewModel.getSelecionarProdutoCommand().executar(produtos.get(selecao - 1).getId());
        }
        
    }

    private void atualizarListaProdutos(Object produtos){
        List<Produto> lista = (List<Produto>) produtos;

        System.out.println("===Lista de Produtos===");
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        }else{
            for(Produto produto : lista){
                System.out.println(produto);
            }
        }
    }

    private void exibirMensagem(Object mensagem){
        if (mensagem != null) {
            System.out.println("🗨️ " + mensagem);
        }
    }


    private void atualizarNome(Object nome){
        System.out.println("Nome atualizado: " + nome);
    }

    private void atualizarPreco(Object preco){
        System.out.println("Preço atualizado: R$ " + preco);
    }

    private void atualizarQuantidade(Object quantidade){
        System.out.println("Quantidade atualizada: " + quantidade);
    }


}
