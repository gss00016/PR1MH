import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Datos {
    private ArrayList<ArrayList<Integer>> flujos;
    private ArrayList<ArrayList<Integer>> distancias;
    private Random random;
    private int dimension;
    private String nombre;

    public Datos(String path) {
        nombre = path.split("/")[1];
        nombre = nombre.substring(0, nombre.length() - 4);
        this.random = new Random();
        flujos = new ArrayList<ArrayList<Integer>>();
        distancias = new ArrayList<ArrayList<Integer>>();
        try {
            FileReader fr = new FileReader(path);
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

    public ArrayList<ArrayList<Integer>> getFlujos() {
        return flujos;
    }

    public ArrayList<ArrayList<Integer>> getDistancias() {
        return distancias;
    }

    public String getNombre() {
        return nombre;
    }

    public Random getRandom() {
        return random;
    }

    public int getDimension() {
        return dimension;
    }
}
