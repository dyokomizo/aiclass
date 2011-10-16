package lib;

import static com.google.common.base.Functions.compose;
import static com.google.common.collect.Iterables.transform;
import lombok.val;

import com.google.common.base.Function;


public class Aggregates {
    public static <F, T> Function<Iterable<F>, Iterable<T>> lift(final Function<F, T> f) {
        return new Function<Iterable<F>, Iterable<T>>() {
            @Override public Iterable<T> apply(Iterable<F> input) {
                return transform(input, f);
            }
        };
    }


    public static <T> Function<Iterable<T>, Integer> sum(Function<T, Integer> f) {
        return compose(sum(), lift(f));
    }


    public static Function<Iterable<Integer>, Integer> sum() {
        return SumFunction.INSTANCE;
    }

    private static enum SumFunction implements Function<Iterable<Integer>, Integer> {
        INSTANCE;
        @Override public Integer apply(Iterable<Integer> input) {
            int sum = 0;
            for (val n : input) {
                sum += (n == null ? 0 : n);
            }
            return sum;
        }
    }
}
