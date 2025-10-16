package mvvm.sistemadeprodutos.binding;

import java.util.HashMap;
import java.util.Map;

import java.util.function.Consumer;

import mvvm.sistemadeprodutos.viewmodel.Command;


public class DataBinder {
    private Map<String, Consumer<Object>> ligacoes = new HashMap<>();

    public <T>  void ligar(T objeto, String propNome, Consumer<Object> atualizadordaView){
        ligacoes.put(propNome, atualizadordaView);

    }

    public void propriedadeAlterada(String propNome, Object novoValor){
        Consumer<Object> atualizador = ligacoes.get(propNome);
        if(atualizador != null){
            atualizador.accept(novoValor);
        }

    }

    public void ligarCommand(Object eventoFonte,String eventoNome, Command command ){
        System.out.println("Comando vinculado: " + eventoNome);
    }

  

}
