import java.util.ArrayList;
import java.util.Collections;

public class BL extends Algoritmo{

    protected ArrayList<Boolean> dlb;

    public BL(Datos d, int semilla, int k) {
        super(d, semilla);
        GreedyAl solInicial = new GreedyAl(d,semilla,k);
        this.solucion = solInicial.getSolucion();
        this.evaluacion = solInicial.evaluacion();
        this.dlb = new ArrayList<>(Collections.nCopies(d.getDimension(), false));
    }
    /*
    * Intercambia las dos posiciones proporcionadas
    * */
    protected void otp2(ArrayList<Integer> arr, int pos1, int pos2){
        int temp = arr.get(pos1);
        arr.set(pos1, arr.get(pos2));
        arr.set(pos2, temp);
    }

    protected void otp2(int pos1, int pos2){
        otp2(solucion,pos1,pos2);
    }

    /*
    * Devuelve la diferencia de evaluación respecto a la actual
    * */
    protected int revaluar(int pos1, int pos2, ArrayList<Integer> arr){
        ArrayList<ArrayList<Integer>> flujos = d.getFlujos();
        ArrayList<ArrayList<Integer>> distancias = d.getDistancias();
        int delta = 0;

        for (int i = 0; i < arr.size(); i++) {
            if(i != pos1 && i != pos2){
                delta += flujos.get(pos1).get(i)*(distancias.get(arr.get(pos2)).get(arr.get(i))-distancias.get(arr.get(pos1)).get(arr.get(i))) +
                         flujos.get(i).get(pos1)*(distancias.get(arr.get(i)).get(arr.get(pos2))-distancias.get(arr.get(i)).get(arr.get(pos1))) +
                         flujos.get(pos2).get(i)*(distancias.get(arr.get(pos1)).get(arr.get(i))-distancias.get(arr.get(pos2)).get(arr.get(i))) +
                         flujos.get(i).get(pos2)*(distancias.get(arr.get(i)).get(arr.get(pos1))-distancias.get(arr.get(i)).get(arr.get(pos2)));
            }
        }

        return delta;
    }

    protected int revaluar(int pos1, int pos2){return revaluar(pos1,pos2,solucion);}
    /*
    * Operador del nuestra busqueda local
    * */
    private void dlb_f(){
        for (int i = 0; i < d.getDimension(); i++) {
            if (!dlb.get(i)) {
                boolean improve_flag = false;
                for (int j = i+1; j < d.getDimension(); j++) {
                    int delta = revaluar(i, j);
                    if(delta < 0){
                        log.info("Movimiento de mejora: ("+i+","+j+")\nTiene una mejora de "+(delta)+"\n");
                        otp2(i, j);
                        evaluacion += delta;
                        dlb.set(i,false);
                        dlb.set(j,false);
                        improve_flag = true;
                    }
                }
                if(!improve_flag){
                    dlb.set(i,true);
                }
            }
        }
    }

    @Override
    protected void calcSolucion() {
        for (int i = 0; i < 5000; i++) {
            log.info("## Iteración "+i+" ##");
            dlb_f();
            Collections.fill(dlb, false);
        }
        evaluacion(solucion);
    }
}
