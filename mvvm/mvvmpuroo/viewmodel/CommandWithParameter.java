package mvvm.mvvmpuroo.viewmodel;


@FunctionalInterface
 public interface CommandWithParameter <T> {

    void execute(T parametro);

}


