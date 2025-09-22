import java.lang.reflect.Array;
import java.util.*;

public class Greedy extends Algoritmo {
    protected ArrayList<Pair> flujosPair;
    protected ArrayList<Pair> distanciasPair;

    public Greedy(Datos d, int semilla) {
        super(d, semilla);
    }

    protected void generarPares() {
        ArrayList<ArrayList<Integer>> flujos = Datos.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = Datos.getDistancias();

        flujosPair = new ArrayList<Pair>(flujos.size());
        distanciasPair = new ArrayList<Pair>(distancias.size());

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
    }

    public ArrayList<Integer> getSolucion() {
        generarPares();
        Queue<Pair> colaDistancias = new LinkedList<>(distanciasPair);
        Stack<Pair> pilaFlujos = new Stack<>();
        pilaFlujos.addAll(flujosPair);
        ArrayList<Integer> solucion = new ArrayList<>(Collections.nCopies(Datos.getDimension(), 0));

        for (int i = 0; i < Datos.getDimension(); i++) {
            solucion.set(pilaFlujos.pop().getFirst(),colaDistancias.poll().getFirst());
        }
        return solucion;
    }


}
