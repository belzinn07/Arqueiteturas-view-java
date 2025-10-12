package mvvm.mvvmpuro.binding;
public interface Observer {
   
  void onPropertyChanged(String propertyName,Object oldValue, Object newValue);

} 