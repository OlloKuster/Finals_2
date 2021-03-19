package edu.kit.informatik.model.data;

import java.util.Objects;

/**
 * @author Oliver Kuster
 * @version 1.0
 *
 * Implements a pairwise data structure
 * @param <A> First argument
 * @param <B> Second argument
 */
public class Pair<A, B> {
    private final A first;
    private final B second;

    /**
     * Constructor of the pair
     * @param first First argument
     * @param second Second argument
     */
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get the first element
     * @return First element
     */
    public A getFirst() {
        return first;
    }

    /**
     * Get the second element
     * @return Second element
     */
    public B getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
