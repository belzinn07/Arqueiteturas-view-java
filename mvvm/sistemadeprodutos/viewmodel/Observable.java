package mvvm.sistemadeprodutos.viewmodel;

import java.util.ArrayList;
import java.util.List;

import mvvm.sistemadeprodutos.binding.Observer;

public class Observable {
    private List<Observer> observadores= new ArrayList<>();

    public void addObserver(Observer observador){
     observadores.add(observador);

    }

    public void removeObserver(Observer observador){
     observadores.remove(observador);

    }

    public void notificarObservador(String propNome, Object valorVelho, Object varlorNovo){
        for(Observer observador : observadores){
            observador.atualizar(propNome, valorVelho, varlorNovo);
        }
    }
}
