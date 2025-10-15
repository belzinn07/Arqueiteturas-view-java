package mvvm.mvvmpuroo.binding;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Databinder {
    
    private  Map<String, Consumer<Object>> bindings = new HashMap<>();

    public void bind(String propertyName, Consumer<Object> updateHandler){
        bindings.put(propertyName, updateHandler);
    }

    public void propertyChanged(String propertyName, Object newValue){
        System.out.println("propertyChanged");
        Consumer<Object> handler = bindings.get(propertyName);
        if(handler != null){
            handler.accept(newValue);
        }

    }
}
