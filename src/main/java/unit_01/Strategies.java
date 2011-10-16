package unit_01;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.compose;
import static com.google.common.collect.Iterables.removeIf;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Ranges.atLeast;
import static com.google.common.collect.Sets.newTreeSet;
import static lib.Extensions.removeMin;
import static unit_01.Node.sizeFunction;

import java.io.Serializable;

import lombok.Data;
import lombok.val;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;


public class Strategies {
    public static <State, Action> Strategy<State, Action> breadthFirst() {
        return new CostStrategy<State, Action>(sizeFunction());
    }


    public static <State, Action> Strategy<State, Action> uniformCost(
            Function<? super Node<State, Action>, Integer> cost) {
        return new CostStrategy<State, Action>(cost);
    }


    public static <State, Action> Strategy<State, Action> aStar(Function<? super Node<State, Action>, Integer> cost,
            Function<? super Node<State, Action>, Integer> distance) {
        return new CostStrategy<State, Action>(total(cost, distance));
    }


    public static <State, Action> Function<? super Node<State, Action>, Integer> total(
            final Function<? super Node<State, Action>, Integer> cost,
            final Function<? super Node<State, Action>, Integer> distance) {
        return new Function<Node<State, Action>, Integer>() {
            @Override public Integer apply(Node<State, Action> input) {
                if (input.getNext() == null) {
                    return cost.apply(input) + distance.apply(input);
                } else {
                    return apply(input.getNext());
                }
            }
        };
    }

    private static @Data class CostStrategy<State, Action> implements Strategy<State, Action>, Serializable {
        private static final long serialVersionUID = 5042181094191225247L;
        private final Function<? super Node<State, Action>, Integer> cost;


        @Override public int compare(Node<State, Action> o1, Node<State, Action> o2) {
            return Ordering.natural().onResultOf(cost).compare(o1, o2);
        }


        @Override public void prune(SearchSpace<State, Action> space) {
            if (space.getSolutions().isEmpty()) { return; }
            val min = newTreeSet(transform(space.getSolutions(), cost)).first();
            removeIf(space.getFrontier(), compose(atLeast(min), cost));
        }


        @Override public Node<State, Action> choose(SearchSpace<State, Action> space) {
            checkArgument(!space.getFrontier().isEmpty());
            return removeMin(space.getFrontier(), Ordering.natural().onResultOf(cost));
        }
    }


    private Strategies() {};
}
