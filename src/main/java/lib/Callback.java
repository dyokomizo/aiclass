package lib;

public interface Callback<T> {
    void execute(T input);
}