import java.util.ArrayList;
import java.util.Collections;

public class BT extends BL {
    private ArrayList<Pair> mCorta;
    private int tenenciaTabu;
    private int iTabu = 0;
    private ArrayList<ArrayList<Integer>> mLarga;
    private float oscilacion;
    private float estancamiento;
    private int parada;
    private int nInt = 0;
    private int nDiv = 0;

    public BT(Datos d,int semilla, int k, int tenenciaTabu, float oscilacion, float estancamiento, int parada) {
        super(d,semilla,k);
        this.tenenciaTabu = tenenciaTabu;
        this.oscilacion = oscilacion;
        this.estancamiento = estancamiento;
        this.parada = parada;
        mCorta = new ArrayList<>(Collections.nCopies(3,new Pair(0,0)));
        mLarga = new ArrayList<>(Collections.nCopies(d.getDimension(),new ArrayList<>(Collections.nCopies(d.getDimension(),0))));
    }

    @Override
    protected void calcSolucion() {
        int oscSinMejora = 0;
        int it = 0;
        int itSinMejora = 0;
        ArrayList<Integer> solActual = (ArrayList<Integer>) solucion.clone();
        int evalActual = evaluacion;
        Pair movMejor = new Pair(0,0);
        int costMejor = 0;
        int nMejoras = 0;

        while (oscSinMejora < parada) {
            boolean dlbAnulado = true;
            boolean improve_flag = false;
            for (int i = 0; i < d.getDimension(); i++) {
                if(!dlb.get(i)){
                    for (int j = 0; j+1 < d.getDimension(); j++) {
                        int delta = revaluar(i,j,solActual);
                        Pair movActual = new Pair(i,j);
                        if(delta < 0 && !mCorta.contains(movActual)){
                            //Aplicamos movimiento
                            otp2(solActual,i,j);
                            evalActual += delta;
                            //Actualizamos memorias
                            mCorta.get(iTabu).set(i,j);iTabu = (iTabu+1)%(tenenciaTabu);
                            mLarga.get(i).set(j,mLarga.get(i).get(j)+1);
                            mLarga.get(j).set(i,mLarga.get(j).get(i)+1);
                            //Actualizamos dbl y si se anula
                            dlb.set(i,false);dlb.set(j,false);
                            dlbAnulado = false;
                            //Actualizamos flag
                            improve_flag = true;

                            nMejoras++;
                            if(evalActual < evaluacion){
                                solucion = solActual;
                                evaluacion = evalActual;
                                itSinMejora = 0;
                                log.info("\n#### Solución global mejorada en iteración "+ it + " ####\n" +
                                        "Evaluación: "+ evaluacion + "\n" +
                                        "Oscilaciones antes de encontrar solución: "+ oscSinMejora + "\n" +
                                        "\tIntensifican: " + nInt + "\n" +
                                        "\tDiversifican: " + nDiv + "\n" +
                                        "Veces mejoradas la solucíon local: "+ nMejoras);
                                oscSinMejora = 0;
                                nMejoras = 0;
                                nInt = 0;
                                nDiv = 0;
                            }
                        } else {
                            if (delta < costMejor) {
                                costMejor = delta;
                                movMejor = movActual;
                            }
                        }
                    }
                    if(!improve_flag){
                        dlb.set(i,true);
                    }
                }
            }

            if(dlbAnulado) {
                //Aplicamos movimiento mejor de los que no mejoran
                otp2(solActual,movMejor.getFirst(),movMejor.getSecond());
                evalActual = evalActual + costMejor;
                //Actualizamos memorias
                mCorta.get(iTabu).set(movMejor.getFirst(),movMejor.getSecond());iTabu = (iTabu+1)%(tenenciaTabu);
                mLarga.get(movMejor.getFirst()).set(movMejor.getSecond(),mLarga.get(movMejor.getFirst()).get(movMejor.getSecond())+1);
                mLarga.get(movMejor.getSecond()).set(movMejor.getFirst(),mLarga.get(movMejor.getSecond()).get(movMejor.getFirst())+1);

                Collections.fill(dlb, false);
            }
            
            itSinMejora++;
            it++;
            if(1-((float)itSinMejora/1000) <= estancamiento){
                oscilacionEstrategica(solActual);
                evalActual = evaluacion(solActual);
                itSinMejora = 0;
                oscSinMejora++;
            }
        }

        log.info("\n" + parada + " oscilaciones sin encontrar un solución mejor. Termina búsqueda.\n" +
                "Evaluación final: " + evaluacion);
    }

    private void oscilacionEstrategica(ArrayList<Integer> solActual) {
        if(d.getRandom().nextInt(100) < oscilacion*100){
            //Intesificación
            nInt++;
            for (int i = 0; i < d.getDimension(); i++) {
                int posMasFrecuente = mLarga.get(i).indexOf(Collections.max(mLarga.get(i)));
                otp2(solActual,i,posMasFrecuente);
            }
        }else {
            //Diversificación
            nDiv++;
            for (int i = 0; i < d.getDimension(); i++) {
                int posMasFrecuente = mLarga.get(i).indexOf(Collections.min(mLarga.get(i)));
                otp2(solActual,i,posMasFrecuente);
            }
        }
    }

}
