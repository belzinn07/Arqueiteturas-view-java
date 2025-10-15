package mvvm.listadeprodutos.binding;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DataBinder {
    private Map<String, Consumer<Object>> ligacoes = new HashMap<>();

    public void ligar(String propNome, Consumer<Object> atualizadordaView){
        ligacoes.put(propNome, atualizadordaView);

    }

    public void propriedadeAlterada(String propNome, Object novoValor){
        Consumer<Object> atualizador = ligacoes.get(propNome);
        if(atualizador != null){
            atualizador.accept(novoValor);
        }

    }

}
