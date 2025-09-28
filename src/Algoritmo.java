import java.util.ArrayList;
import java.util.Random;

public class Algoritmo {
    protected Datos d;
    protected Random random;
    protected ArrayList<Integer> solucion;

    public Algoritmo(ArrayList<String> parametros) {}

    public Algoritmo(Datos d, int semilla) {
        this.d = d;
        this.random = new Random(semilla);
    }

    protected void calcSolucion(){}

    public ArrayList<Integer> getSolucion(){
        if(solucion == null){
            calcSolucion();
        }
        return solucion;
    }

    public Integer evaluacion() {
        ArrayList<ArrayList<Integer>> flujos = d.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = d.getDistancias();
        Integer sum = 0;
        for(int i = 0; i < solucion.size(); i++) {
            for(int j = 0; j < solucion.size(); j++) {
                if(i != j){
                    sum += flujos.get(i).get(j) * distancias.get(solucion.get(i)).get(solucion.get(j));
                }
            }
        }
        return sum;
    }
}
