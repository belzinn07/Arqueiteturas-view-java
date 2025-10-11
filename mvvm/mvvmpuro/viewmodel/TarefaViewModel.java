package mvvm.mvvmpuro.viewmodel;
import mvvm.mvvmpuro.viewmodel.Command;
import mvvm.mvvmpuro.viewmodel.CommandWithParameter;
import java.util.ArrayList;
import java.util.List;
import mvvm.mvvmpuro.model.Tarefa;


public class TarefaViewModel extends Observable{

    private final List<Tarefa> tarefas = new ArrayList<>();

    //commands
    private final Command carregarTarefasCommand; 
    private final Command adicionarTarefaCommand;
    private final CommandWithParameter<Integer> removerTarefaCommand;
    private final CommandWithParameter<Integer> concluirTarefaCommand;

    

    
}
