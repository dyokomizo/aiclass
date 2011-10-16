package unit_01;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Predicates.equalTo;
import lombok.val;

import com.google.common.base.Predicate;


public class GraphSearch<State, Action> {
    private final Problem<State, Action> problem;
    private final Strategy<State, Action> strategy;


    public GraphSearch(Problem<State, Action> problem, Strategy<State, Action> strategy) {
        checkNotNull(problem);
        checkNotNull(strategy);
        this.problem = problem;
        this.strategy = strategy;
    }


    public Iterable<Node<State, Action>> search(State initial, Predicate<? super State> goal) {
        val space = new SearchSpace<State, Action>(initial, goal);
        for (int i = 0; i < 100; i++) {
            strategy.prune(space);
            if (space.getFrontier().isEmpty()) { return space.getSolutions(); }
            val path = strategy.choose(space);
            val state = path.getEnd();
            space.explore(path);
            for (val action : problem.actions(state)) {
                val result = problem.result(state, action);
                space.consider(path.via(action, result));
            }
        }
        throw new IllegalStateException("Failed!");
    }


    public SearchFrom from(State initial) {
        return new SearchFrom(initial);
    }

    public class SearchFrom {
        private final State initial;


        private SearchFrom(State initial) {
            this.initial = initial;
        }


        public Iterable<Node<State, Action>> to(State state) {
            return until(equalTo(state));
        }


        public Iterable<Node<State, Action>> until(Predicate<? super State> goal) {
            return search(initial, goal);
        }
    }
}
