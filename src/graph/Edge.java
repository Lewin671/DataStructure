package graph;

import java.util.Objects;

// 无权边
public class Edge {
    int from, to;

    Edge(int _first, int _second) {
        from = _first;
        to = _second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge pair = (Edge) o;
        return from == pair.from &&
                to == pair.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
