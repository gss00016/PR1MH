import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class Algoritmo {
    protected Datos d;
    protected ArrayList<Integer> solucion;
    protected int evaluacion = 0;
    protected Logger log;

    public Algoritmo(Datos d, int semilla) {
        this.d = d;
        log = Log.getLogger(getClass().getName()+semilla+d.getNombre());
    }

    protected void calcSolucion(){}

    public ArrayList<Integer> getSolucion(){
        if(solucion == null){
            calcSolucion();
        }
        return solucion;
    }

    public int evaluacion(){
        if(evaluacion == 0){
            evaluacion = evaluacion(getSolucion());
        }
        return evaluacion;
    }

    public int evaluacion(ArrayList<Integer> solucion) {
        ArrayList<ArrayList<Integer>> flujos = d.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = d.getDistancias();
        int sum = 0;
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
