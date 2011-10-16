package lib;

import static com.google.common.base.Functions.toStringFunction;

import java.io.PrintStream;

import lombok.val;

import com.google.common.base.Function;
import com.google.common.base.Predicate;


public class Callbacks {
    private Callbacks() {}


    public static Callback<Object> println(final PrintStream out) {
        return println(toStringFunction(), out);
    }


    public static <T> Callback<T> println(final Function<? super T, String> format, final PrintStream out) {
        return new Callback<T>() {
            @Override public void execute(T input) {
                out.println(format.apply(input));
            }
        };
    }


    public static <T> Predicate<T> callWhen(final Callback<? super T> callback, final Predicate<T> predicate) {
        return new Predicate<T>() {
            @Override public boolean apply(T input) {
                val result = predicate.apply(input);
                if (result) {
                    callback.execute(input);
                }
                return result;
            }
        };
    }
}