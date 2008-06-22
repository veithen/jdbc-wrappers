public class Holder<T> {
    private boolean set;
    private T value;
    
    public void set(T value) {
        this.value = value;
        set = true;
    }
    
    public T get() {
        if (!set) {
            throw new IllegalStateException();
        }
        return value;
    }
    
    public boolean isSet() {
        return set;
    }
}
