package mvvm.sistemadeprodutos.viewmodel;
@FunctionalInterface
public interface CommandComParametro<T> {
     void executar(T parametro);
    
}
