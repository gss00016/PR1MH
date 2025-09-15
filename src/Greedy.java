import java.lang.reflect.Array;
import java.util.*;

public class Greedy {
    private Datos d;
    public Greedy() {}
    public Greedy(Datos d) {
        this.d = d;
    }

    public ArrayList<Integer> getSolucion() {
        ArrayList<ArrayList<Integer>> flujos = Datos.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = Datos.getDistancias();
        ArrayList<Integer> solucion = new ArrayList<>(Collections.nCopies(flujos.size(), null));

        ArrayList<Pair> flujosPair = new ArrayList<Pair>(flujos.size());
        ArrayList<Pair> distanciasPair = new ArrayList<Pair>(distancias.size());

        for (int i = 0; i < flujos.size(); i++) {
            Integer f_sum = 0;
            Integer d_sum = 0;
            for (int j = 0; j < flujos.get(i).size(); j++) {
                f_sum += flujos.get(i).get(j);
                d_sum += distancias.get(i).get(j);
            }
            flujosPair.add(new Pair(i, f_sum));
            distanciasPair.add(new Pair(i, d_sum));
        }
        flujosPair.sort(new Pair.CompSecond());
        distanciasPair.sort(new Pair.CompSecond());

        Queue<Pair> colaDistancias = new LinkedList<>(flujosPair);
        Stack<Pair> pilaFlujos = new Stack<>();
        pilaFlujos.addAll(distanciasPair);

        for (int i = 0; i < flujos.size(); i++) {
            solucion.set(pilaFlujos.pop().getFirst(),colaDistancias.poll().getFirst());
        }
        return solucion;
    }


}
