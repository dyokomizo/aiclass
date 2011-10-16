package unit_01;

import java.util.Comparator;


public interface Strategy<State, Action> extends Comparator<Node<State, Action>> {
    void prune(SearchSpace<State, Action> space);


    Node<State, Action> choose(SearchSpace<State, Action> space);
}