package lib;

import static com.google.common.collect.Ordering.from;

import java.util.Collection;
import java.util.Comparator;

import lombok.val;

import com.google.common.base.Objects;


public class Extensions {
    private Extensions() {}


    public static <T> T removeMin(Iterable<T> values, Comparator<? super T> comparator) {
        val min = from(comparator).min(values);
        removeFirst(values, min);
        return min;
    }


    public static <T> boolean removeFirst(Iterable<T> removeFrom, T element) {
        if (removeFrom instanceof Collection<?>) {
            val collection = (Collection<?>) removeFrom;
            return collection.remove(element);
        } else {
            for (val i = removeFrom.iterator(); i.hasNext();) {
                val path = i.next();
                if (Objects.equal(element, path)) {
                    i.remove();
                    return true;
                }
            }
        }
        return false;
    }
}