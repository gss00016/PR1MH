import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;

public class Greedy extends Algoritmo {
    protected ArrayList<Pair> flujosPair;
    protected ArrayList<Pair> distanciasPair;

    public Greedy(Datos d, int semilla) {
        super(d, semilla);
    }

    protected void generarPares() {
        ArrayList<ArrayList<Integer>> flujos = d.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = d.getDistancias();
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
        log.info("5 mayores flujos: "+flujosPair.subList(flujos.size()-6,flujos.size()-1).reversed().toString());
        distanciasPair.sort(new Pair.CompSecond());
        log.info("5 menores distancias: "+distanciasPair.subList(0,5).toString());
    }

    public void calcSolucion() {
        generarPares();
        Queue<Pair> colaDistancias = new LinkedList<>(distanciasPair);
        Stack<Pair> pilaFlujos = new Stack<>();
        pilaFlujos.addAll(flujosPair);
        solucion = new ArrayList<>(Collections.nCopies(d.getDimension(), 0));

        for (int i = 0; i < d.getDimension(); i++) {
            solucion.set(pilaFlujos.pop().getFirst(),colaDistancias.poll().getFirst());
        }
        log.info("\nSolución: "+solucion.toString());
    }
}
