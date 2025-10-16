package mvvm.sistemadeprodutos.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import mvvm.sistemadeprodutos.binding.DataBinder;
import mvvm.sistemadeprodutos.model.Produto;
import mvvm.sistemadeprodutos.viewmodel.ProdutoViewModel;

public class ProdutoView {
    private ProdutoViewModel viewModel;
    private DataBinder binder;
    private Scanner scanner;
    private boolean executando;

    public ProdutoView(ProdutoViewModel produtoViewModel){
        this.viewModel = produtoViewModel;
        this.binder = new DataBinder();
        this.scanner = new Scanner(System.in);
        configuraDataBinding();
    }

    private void configuraDataBinding(){
        binder.ligar(viewModel, ProdutoViewModel.PROP_PRODUTOS, this::atualizarListaProdutos);
        binder.ligar(viewModel, ProdutoViewModel.PROP_MENSAGEM, this::exibirMensagem);
        binder.ligar(viewModel, ProdutoViewModel.PROP_NOME, this::atualizarNome);
        binder.ligar(viewModel, ProdutoViewModel.PROP_PRECO, this::atualizarPreco);
        binder.ligar(viewModel, ProdutoViewModel.PROP_QUANTIDADE, this::atualizarQuantidade);

        binder.ligarCommand(this, "carregarProdutos", viewModel.getCarregarProdutoCommand());
        binder.ligarCommand(this, "adicionarProdutos", viewModel.getAdicionarProdutoCommand());
        binder.ligarCommand(this, "atualizarProdutos", viewModel.getAtualizarProdutCommand()); // Correção do nome do método
        binder.ligarCommand(this, "excluirProduto", viewModel.getExcluiProdutoCommand());
    }

    public void iniciar(){
        executando = true;
        System.out.println("=======SISTEMA DE PRODUTOS=======");

        while (executando) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); 
                processarOpcao(opcao);
            } catch (InputMismatchException e) {
                System.out.println("⚠️ Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); 
            }
        }
    }

    public void exibirMenu(){
        System.out.println("\n======MENU PRODUTOS======");
        System.out.println("1. Listar Produtos");
        System.out.println("2. Adicionar Produto");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Excluir Produto");
        System.out.println("5. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
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
                if (selecionarProdutoParaEdicao()) {
                    lerDadosProduto();
                    viewModel.getAtualizarProdutCommand().executar();
                }
                break;
               
            case 4:
                if (selecionarProdutoParaExclusao()) {
                    viewModel.getExcluiProdutoCommand().executar();
                }
                break;
               
            case 5:
                executando = false;
                System.out.println("Voltando ao menu principal...");
                break;   
                
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void lerDadosProduto(){
        System.out.print("Nome do Produto: ");
        viewModel.setNome(scanner.nextLine());

        System.out.print("Preço: ");
        viewModel.setPreco(scanner.nextLine());

        System.out.print("Quantidade: ");
        viewModel.setQuantidade(scanner.nextLine());
    }
    
   
    private boolean selecionarProdutoParaEdicao() {
        List<Produto> produtos = viewModel.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado. ");
            return false;
        }

        System.out.println("\n--- Selecione um Produto para Edição ---");
        exibirListaProdutos(produtos);

        System.out.print("Selecione o número do produto: ");
        try {
            int selecao = scanner.nextInt();
            scanner.nextLine(); 
            if (selecao > 0 && selecao <= produtos.size()) {
                viewModel.getSelecionarProdutoCommand().executar(produtos.get(selecao - 1).getId());
                return true;
            } else {
                System.out.println("Seleção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine();
        }
        return false;
    }

    
    private boolean selecionarProdutoParaExclusao() {
        List<Produto> produtos = viewModel.getProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado. ");
            return false;
        }

        System.out.println("\n--- Selecione um Produto para Exclusão ---");
        exibirListaProdutos(produtos);

        System.out.print("Selecione o número do produto: ");
        try {
            int selecao = scanner.nextInt();
            scanner.nextLine();
            if (selecao > 0 && selecao <= produtos.size()) {
                viewModel.getSelecionarProdutoCommand().executar(produtos.get(selecao - 1).getId());
                return true;
            } else {
                System.out.println("Seleção inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); 
        }
        return false;
    }

    private void exibirListaProdutos(List<Produto> produtos) {
        for(int i = 0; i < produtos.size() ; i++){
            System.out.println((i + 1) + ". " + produtos.get(i).getNome() + " | Preço: R$" + produtos.get(i).getPreco() + " | Qtd: " + produtos.get(i).getQuantidade());
        }
    }

    
    private void atualizarListaProdutos(Object produtos){
        List<Produto> lista = (List<Produto>) produtos;
        System.out.println("=== Lista de Produtos ===");
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            exibirListaProdutos(lista);
        }
    }

    private void exibirMensagem(Object mensagem){
        if (mensagem != null) {
            System.out.println("🗨️ " + mensagem);
        }
    }

    private void atualizarNome(Object nome){
     
    }

    private void atualizarPreco(Object preco){
   
    }

    private void atualizarQuantidade(Object quantidade){
    
    }
}
