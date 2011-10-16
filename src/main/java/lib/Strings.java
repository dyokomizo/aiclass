package lib;

import java.text.Format;

import lombok.val;

import com.google.common.base.Function;
import com.google.common.base.Supplier;


public class Strings {
    public static Function<Object, String> format(final Supplier<? extends Format> supplier) {
        return new Function<Object, String>() {
            @Override public String apply(Object input) {
                val format = supplier.get();
                return format.format(input);
            }
        };
    }


    public static Function<Object, String> prefix(final String... prefixes) {
        return new Function<Object, String>() {
            @Override public String apply(Object input) {
                final StringBuilder s = new StringBuilder();
                for (val prefix : prefixes) {
                    s.append(prefix);
                }
                s.append(input);
                return s.toString();
            }
        };
    }


    public static Function<Object, String> suffix(final String... suffixes) {
        return new Function<Object, String>() {
            @Override public String apply(Object input) {
                final StringBuilder s = new StringBuilder();
                s.append(input);
                for (val suffix : suffixes) {
                    s.append(suffix);
                }
                return s.toString();
            }
        };
    }
}