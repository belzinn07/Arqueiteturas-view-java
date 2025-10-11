package mvvm.mvvmpuro.binding;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Databinder {
    
    private final Map<String, Consumer<Object>> bindings = new HashMap<>();

    public void bind(String propertyName, Consumer<Object> updateHandler){
        bindings.put(propertyName, updateHandler);
    }

    public void propertyChanged(String propertyName, Object newValue){
        Consumer<Object> handler = bindings.get(propertyName);
        if(handler != null){
            handler.accept(newValue);
        }

    }
}
