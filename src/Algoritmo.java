import java.util.ArrayList;
import java.util.Random;

public class Algoritmo {
    protected Datos d;
    protected Random random;

    public Algoritmo(ArrayList<String> parametros) {}

    public Algoritmo(Datos d, int semilla) {
        this.d = d;
        this.random = new Random(semilla);
    }

    public ArrayList<Integer> getSolucion(){return null;}
}
