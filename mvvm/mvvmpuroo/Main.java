package mvvm.mvvmpuroo;

import mvvm.mvvmpuroo.view.TarefaView;
import mvvm.mvvmpuroo.viewmodel.TarefaViewModel;

public class Main {
    public static void main(String[] args) {
        TarefaViewModel viewModel = new TarefaViewModel();
        TarefaView view = new TarefaView(viewModel);
           view.iniciar();

    }
}
