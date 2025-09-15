import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Datos {
    private static ArrayList<ArrayList<Integer>> flujos;
    private static ArrayList<ArrayList<Integer>> distancias;
    public Datos(String path) {
        int dimension;
        flujos = new ArrayList<ArrayList<Integer>>();
        distancias = new ArrayList<ArrayList<Integer>>();
        try {
            FileReader fr = new FileReader("_ejemplo.txt");
            BufferedReader br = new BufferedReader(fr);
            dimension = Integer.parseInt(br.readLine());
            br.readLine();

            for (int i = 0; i < dimension; i++) {
                String[] l =  br.readLine().split(" ");
                flujos.add(new ArrayList<Integer>());
                for (String s : l) {
                    flujos.get(i).add(Integer.parseInt(s));
                }
            }
            br.readLine();
            for (int i = 0; i < dimension; i++) {
                String[] l =  br.readLine().split(" ");
                distancias.add(new ArrayList<Integer>());
                for (String s : l) {
                    distancias.get(i).add(Integer.parseInt(s));
                }
            }
            br.close();
            fr.close();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static ArrayList<ArrayList<Integer>> getFlujos() {
        return flujos;
    }

    public static ArrayList<ArrayList<Integer>> getDistancias() {
        return distancias;
    }

    public Integer evaluacion(ArrayList<Integer> solucion) {
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
