package mvvm.mvvmpuro.viewmodel;


@FunctionalInterface
 public interface CommandWithParameter <T> {

    void execute(T parametro);

}


