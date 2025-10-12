package mvvm.mvvmpuro.viewmodel;
import mvvm.mvvmpuro.viewmodel.Command;
import mvvm.mvvmpuro.viewmodel.CommandWithParameter;
import java.util.ArrayList;
import java.util.List;
import mvvm.mvvmpuro.model.Tarefa;


public class TarefaViewModel extends Observable{

    private final List<Tarefa> tarefas = new ArrayList<>();

     public static final String PROP_TAREFAS = "tarefas";


    //commands
    private final Command carregarTarefasCommand; 
    private final Command adicionarTarefaCommand;
    private final CommandWithParameter<Integer> removerTarefaCommand;
    private final CommandWithParameter<Integer> concluirTarefaCommand;

    public TarefaViewModel(){

        carregarTarefasCommand = this::carregarTarefas;
        adicionarTarefaCommand = () -> adicionarTarefa( "Nova Tarefa " + (tarefas.size() + 1));
        removerTarefaCommand = this::removerTarefa;
        concluirTarefaCommand = this::concluirTarefa;
  
}

   public List<Tarefa> getTarefas(){
      return tarefas;
   }

   public Command getCarregasTarefasCommand(){
    return carregarTarefasCommand;
   }
   public Command getAdicionaTarefaCommand(){
    return adicionarTarefaCommand;
   }
   public CommandWithParameter<Integer> getRemoverTarefasCommand(){
    return removerTarefaCommand;
   }
   public CommandWithParameter<Integer> getConcluirTarefasCommand(){
    return concluirTarefaCommand;
   }

   private void carregarTarefas(){
    notifyObservers(PROP_TAREFAS, null, new ArrayList<>(tarefas));
   }

   private void adicionarTarefa(String descricao){
    tarefas.add(new Tarefa(descricao));
    notifyObservers(PROP_TAREFAS, null,new ArrayList<>(tarefas));
   }

   private void removerTarefa(Integer index){
    if (index >= 0 && index< tarefas.size()) {
        tarefas.remove((int) index);
        notifyObservers(PROP_TAREFAS, null, new ArrayList<>(tarefas));
        
    }else{
        System.out.println("falha ao remover tarefa");
    }

   }

   private void concluirTarefa(Integer index){
    if(index >= 0 && index < tarefas.size()){
        tarefas.get(index).concluir();
        notifyObservers(PROP_TAREFAS, null, new ArrayList<>(tarefas));
    }
   }

}
