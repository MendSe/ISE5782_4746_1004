package primitives;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *Class to find the median of a list using a comparator
 */
public class Median<T> {
    final List<T> list;
    final Comparator<T> comp;

    public Median(List<T> list, Comparator<T> comp) {
        this.list = list;
        this.comp = comp;
    }

    /**
     * It takes a list, a left index, a right index, and a k value, and returns the kth smallest element in the list
     *
     * @param list the list of elements to be sorted
     * @param left the leftmost index of the list
     * @param right the last index of the list
     * @param k the index of the element we want to find
     * @return The kth smallest element in the list.
     */
    public T select(List<T> list, int left, int right, int k) {
        while (left < right) {
            T pivot = list.get(k);
            Collections.swap(list, k, right);

            int pos;
            for (int i = pos = left; i < right; i++) {
                if (comp.compare(list.get(i), pivot) < 0) {
                    Collections.swap(list, i, pos);
                    pos++;
                }
            }
            Collections.swap(list, right, pos);
            if (pos == k)
                break;
            if (pos < k)
                left = pos + 1;
            else
                right = pos - 1;
        }
        return list.get(k);
    }

    /**
     * It finds the median of a list of numbers.
     *
     * @return The median of the list.
     */
    public T findMedian() {
        return select(list, 0, list.size() - 1, (list.size() - 1) / 2);
    }
}