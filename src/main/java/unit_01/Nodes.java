package unit_01;

import com.google.common.base.Function;


public class Nodes {
    public static <E> Function<Node<?, E>, E> edgeFunction() {
        return new Function<Node<?, E>, E>() {
            @Override public E apply(Node<?, E> input) {
                return input.getEdge();
            }
        };
    }


    public static <V> Function<Node<V, ?>, V> vertexFunction() {
        return new Function<Node<V, ?>, V>() {
            @Override public V apply(Node<V, ?> input) {
                return input.getVertex();
            }
        };
    }


    public static <E> Function<Node<?, E>, Iterable<E>> edgesFunction() {
        return new Function<Node<?, E>, Iterable<E>>() {
            @Override public Iterable<E> apply(Node<?, E> input) {
                return input.edges();
            }
        };
    }


    public static <V> Function<Node<V, ?>, Iterable<V>> verticesFunction() {
        return new Function<Node<V, ?>, Iterable<V>>() {
            @Override public Iterable<V> apply(Node<V, ?> input) {
                return input.vertices();
            }
        };
    }


    private Nodes() {}
}