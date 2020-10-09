package graph;

import java.util.Objects;

// 有权边
public class WeightEdge {
    int from, to, weight;

    WeightEdge(int _from, int _to, int _weight) {
        from = _from;
        to = _to;
        weight = _weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightEdge that = (WeightEdge) o;
        return from == that.from &&
                to == that.to &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }
}
