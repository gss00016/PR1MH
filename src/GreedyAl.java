import java.util.*;

public class GreedyAl extends Greedy{
    private int k;
    public GreedyAl(Datos d, int semilla, int k) {
        super(d, semilla);
        this.k = k;
    }

    @Override
    public void calcSolucion() {
        generarPares();
        Queue<Pair> colaDistancias = new LinkedList<>(distanciasPair);
        solucion = new ArrayList<>(Collections.nCopies(d.getDimension(), 0));

        for (int i = 0; i < d.getDimension(); i++) {
            int indice = k < flujosPair.size() ? d.getRandom().nextInt(k) : flujosPair.size() - 1;
            solucion.set(flujosPair.get(indice).getFirst(), colaDistancias.poll().getFirst());
            flujosPair.remove(indice);
        }
        log.info("\nSolución: "+solucion.toString());
    }
}
