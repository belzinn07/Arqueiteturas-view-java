package mvvm.sistemadeprodutos;

import java.util.Scanner;


import mvvm.sistemadeprodutos.service.ProdutoService;
import mvvm.sistemadeprodutos.view.ProdutoView;
import mvvm.sistemadeprodutos.viewmodel.ProdutoViewModel;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;
        ProdutoService produtoService = new ProdutoService();

        System.out.println("===SISTEMA DE PRODUTOS===");

        while (executando) {
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    iniciarSistemaProdutos(produtoService);
                    break;
                
                case 3:
                    executando = false;
                    System.out.println("Saindo do Sistema...");    
            
                default:
                System.out.println("Opção inválida");
                    break;
            }
        }
        scanner.close();
    }

    public static void exibirMenuPrincipal(){

       System.out.println("===MENU===");
       System.out.println("1. Gerenciar Produtos");
       System.out.println("2. Sair");
       System.out.println("Escolha uma opção: ");
    }

    public static void iniciarSistemaProdutos(ProdutoService produtoService){
        ProdutoViewModel produtoViewModel = new ProdutoViewModel(produtoService);
        ProdutoView produtoView = new ProdutoView(produtoViewModel);
        produtoView.iniciar();

    }
}
