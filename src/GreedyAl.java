import java.util.*;

public class GreedyAl extends Greedy{
    private int k;
    public GreedyAl(Datos d, int semilla, int k) {
        super(d, semilla);
        this.k = k;
    }

    @Override
    public ArrayList<Integer> getSolucion() {
        generarPares();
        ArrayList<Integer> solucion = new ArrayList<>(Collections.nCopies(Datos.getDimension(), 0));
        Queue<Pair> colaDistancias = new LinkedList<>(distanciasPair);

        for (int i = 0; i < Datos.getDimension(); i++) {
            int indice = k < flujosPair.size() ? random.nextInt(k) : flujosPair.size() - 1;
            solucion.set(flujosPair.get(indice).getFirst(), colaDistancias.poll().getFirst());
            flujosPair.remove(indice);
        }
        return solucion;
    }
}
