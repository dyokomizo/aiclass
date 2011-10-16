package unit_01;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.val;

import com.google.common.base.Predicate;


public @Data class SearchSpace<State, Action> {
    private final Predicate<? super State> goal;
    private final Set<Node<State, Action>> frontier;
    private final Set<State> explored;
    private final Set<Node<State, Action>> solutions;


    public SearchSpace(State initial, Predicate<? super State> goal) {
        checkNotNull(initial);
        checkNotNull(goal);
        this.goal = goal;
        this.frontier = new HashSet<Node<State, Action>>();
        this.explored = new HashSet<State>();
        this.solutions = new HashSet<Node<State, Action>>();
        val path = new Node<State, Action>(initial);
        if (goal.apply(initial)) {
            solutions.add(path);
        } else {
            frontier.add(path);
        }
    }


    public void explore(Node<State, Action> path) {
        explored.add(path.getEnd());
    }


    public void consider(Node<State, Action> path) {
        if (explored.contains(path.getEnd())) { return; }
        if (getGoal().apply(path.getEnd())) {
            solutions.add(path);
        } else {
            frontier.add(path);
        }
    }
}