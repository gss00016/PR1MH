import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Datos> datos = new ArrayList<>();
        String[] parametros;
        Algoritmo al=null;

        try {
            FileReader fr = new FileReader("parametros.csv");
            BufferedReader br = new BufferedReader(fr);

            for(String fichero : br.readLine().split(";")){
                datos.add(new Datos(fichero));
            }

            while (br.ready()) {
                parametros = br.readLine().split(";");
                for (Datos dato : datos) {
                    switch (parametros[0]) {
                        case "GreedyAl":
                            al = new GreedyAl(dato, Integer.parseInt(parametros[1]), Integer.parseInt(parametros[2]));
                            System.out.println(dato.getDimension());
                            break;
                    }
                    System.out.println(al.getSolucion());
                }

            }
            br.close();
            fr.close();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }

        /*Datos d;
        Greedy g;
        for (int i = 1; i < 5; i++) {
            String path = "datos/ford0"+i+".dat";
            System.out.println(path);
            d = new Datos(path);
            g = new GreedyAl(d,4,6);
            System.out.println(g.getSolucion());
            System.out.println(d.evaluacion(g.getSolucion()));
        }*/

    }
}