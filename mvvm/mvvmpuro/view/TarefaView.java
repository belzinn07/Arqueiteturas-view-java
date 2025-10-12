package mvvm.mvvmpuro.view;

import java.util.Scanner;
import java.util.List;
import mvvm.mvvmpuro.model.Tarefa;
import mvvm.mvvmpuro.binding.Databinder;
import mvvm.mvvmpuro.viewmodel.TarefaViewModel;

public class TarefaView {
      private final TarefaViewModel viewModel;
      private final Databinder binder = new Databinder();
      private final Scanner scanner = new Scanner(System.in);

      public TarefaView(TarefaViewModel vm){
        this.viewModel = vm;

        viewModel.addObserver((propertyName,oldValue, newValue) -> {
            binder.propertyChanged(propertyName, newValue);
       
         } );

    binder.bind(TarefaViewModel.PROP_TAREFAS, valor -> mostrarTarefas((List<Tarefa>) valor));
    
}

public void iniciar(){
    boolean rodando = true;

    while (rodando) {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Carregar Tarefas");
        System.out.println("2. Adicionar Tarefa");
        System.out.println("3. Remover Tarefa");
        System.out.println("4. Concluir Tarefa");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");

        int opcao;

        try{
            opcao = Integer.parseInt(scanner.nextLine().trim());
        }catch(Exception e){
            System.out.println("Opção inválida.");
            continue;
        }

        switch (opcao) {
            case 1:
                viewModel.getCarregasTarefasCommand().execute();
                break;
            case 2:
                viewModel.getAdicionaTarefaCommand().execute();
                break;
           case 3:
                System.out.print("Índice da tarefa a remover: "); 
                 
                try {
                    int index = Integer.parseInt(scanner.nextLine().trim());
                    viewModel.getRemoverTarefasCommand().execute(index);
                } catch (Exception e) {
                    System.out.println("Índice inválido.");
                }
                break;
            case 4:
                System.out.print("Índice da tarefa a concluir: ");
                try {
                    int index = Integer.parseInt(scanner.nextLine().trim());
                    viewModel.getConcluirTarefasCommand().execute(index);
                } catch (NumberFormatException e) {
                    System.out.println("Índice inválido.");
                }
                break;
                
            case 5:
                rodando = false;
                System.out.println("Saindo do programa.");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    } 
    
}

private void mostrarTarefas(List<Tarefa> tarefas){
    System.out.println("\n=== TAREFAS ===");
    if (tarefas == null || tarefas.isEmpty()) {
        System.out.println("Nenhuma tarefa encontrada.");
        return;
    }
    for (int i = 0; i < tarefas.size(); i++) {
        System.out.println(i + ". " + tarefas.get(i) + " " + tarefas.get(i).getDescricao());
    }

        
    }

}