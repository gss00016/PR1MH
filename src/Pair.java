import java.util.Comparator;
import java.util.Objects;

public class Pair {
    private Integer first;
    private Integer second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + first + ", second=" + second + '}';
    }

    public static class CompSecond implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return Comparator
                    .comparing(Pair::getSecond, Comparator.nullsFirst(Integer::compareTo))
                    .compare(o1, o2);
        }
    }
}
