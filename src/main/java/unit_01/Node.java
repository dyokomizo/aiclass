package unit_01;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.immutableEntry;

import java.util.Iterator;
import java.util.Map;

import lib.MapEntries;
import lombok.Data;
import lombok.val;

import com.google.common.base.Function;
import com.google.common.collect.AbstractIterator;


public @Data class Node<V, E> implements Iterable<Map.Entry<E, V>> {
    public static Function<Node<?, ?>, Integer> sizeFunction() {
        return new Function<Node<?, ?>, Integer>() {
            @Override public Integer apply(Node<?, ?> input) {
                return input.size();
            }
        };
    }


    public static <V> Function<Node<V, ?>, V> endFunction() {
        return new Function<Node<V, ?>, V>() {
            @Override public V apply(Node<V, ?> input) {
                return input.getEnd();
            }
        };
    }

    private final E edge;
    private final V vertex;
    private final Node<V, E> next;


    public Node(V vertex) {
        checkNotNull(vertex);
        this.edge = null;
        this.vertex = vertex;
        this.next = null;
    }


    public Node(E edge, V vertex) {
        checkNotNull(edge);
        checkNotNull(vertex);
        this.edge = edge;
        this.vertex = vertex;
        this.next = null;
    }


    private Node(Node<V, E> path, Node<V, E> next) {
        checkNotNull(path);
        checkNotNull(next);
        this.edge = path.edge;
        this.vertex = path.vertex;
        this.next = next;
    }


    public Node<V, E> via(E edge, V vertex) {
        return new Node<V, E>(this, (next == null) ? new Node<V, E>(edge, vertex) : next.via(edge, vertex));
    }


    public V getEnd() {
        return next == null ? vertex : next.getEnd();
    }


    public int size() {
        return next == null ? 0 : 1 + next.size();
    }


    public Iterable<E> edges() {
        return filter(transform(this, MapEntries.<E> keyFunction()), notNull());
    }


    public Iterable<V> vertices() {
        return filter(transform(this, MapEntries.<V> valueFunction()), notNull());
    }


    public Iterator<Map.Entry<E, V>> iterator() {
        return new AbstractIterator<Map.Entry<E, V>>() {
            private Node<V, E> path = Node.this;


            @Override protected Map.Entry<E, V> computeNext() {
                if (path == null) {
                    return endOfData();
                } else {
                    val current = path;
                    path = path.next;
                    return current.toMapEntry();
                }
            }
        };
    }


    public Map.Entry<E, V> toMapEntry() {
        return immutableEntry(edge, vertex);
    }


    @Override public String toString() {
        val s = new StringBuilder();
        appendTo(s);
        return s.toString();
    }


    private void appendTo(StringBuilder s) {
        if (edge != null) {
            s.append(edge);
            s.append(' ');
        }
        s.append(vertex);
        if (next != null) {
            s.append(' ');
            next.appendTo(s);
        }
    }
}