package mvvm.mvvmpuro;

import mvvm.mvvmpuro.viewmodel.TarefaViewModel;
import mvvm.mvvmpuro.view.TarefaView;

public class Main {
    public static void main(String[] args) {
        TarefaViewModel viewModel = new TarefaViewModel();
        TarefaView view = new TarefaView(viewModel);
           view.iniciar();

    }
}
