package mvvm.mvvmpuro.viewmodel;




@FunctionalInterface
public interface Command {
  
    void execute();
    
}

@FunctionalInterface
 interface CommandWithParameter <T> {

    void execute(T parametro);

}

