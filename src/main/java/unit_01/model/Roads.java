package unit_01.model;

import com.google.common.base.Function;

public class Roads {
    public static Function<Road, Integer> costFunction() {
        return new Function<Road, Integer>() {
            @Override public Integer apply(Road input) {
                return input.getCost();
            }
        };
    }


    private Roads() {}
}