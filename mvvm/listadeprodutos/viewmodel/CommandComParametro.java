package mvvm.listadeprodutos.viewmodel;
@FunctionalInterface
public interface CommandComParametro<T> {
     void executar(T parametro);
    
}
