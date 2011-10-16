package unit_01;

public interface Problem<State, Action> {
    Iterable<Action> actions(State s);


    State result(State s, Action a);
}