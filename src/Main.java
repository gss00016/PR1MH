import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Datos> datos = new ArrayList<>();
        String[] parametros;
        Algoritmo al=null;
        Logger logMain = Log.getLogger("main");
        try {
            FileReader fr = new FileReader("parametros.csv");
            BufferedReader br = new BufferedReader(fr);

            for(String fichero : br.readLine().split(";")){
                datos.add(new Datos(fichero));
            }

            while (br.ready()) {
                parametros = br.readLine().split(";");
                logMain.info("<#############> "+parametros[0]+" <#############> "+parametros[1]+" <#############>\n"+
                            "\tNombre\t\tEvaluación\tTiempo");

                for (Datos dato : datos) {
                    switch (parametros[0]) {
                        case "GE":
                            al = new Greedy(dato, Integer.parseInt(parametros[1]));
                            break;
                        case "GA":
                            al = new GreedyAl(dato, Integer.parseInt(parametros[1]), Integer.parseInt(parametros[2]));
                            break;
                        case "BL":
                            al = new BL(dato, Integer.parseInt(parametros[1]), Integer.parseInt(parametros[2]));
                            break;
                        case "BT":
                            al = new BT(dato, Integer.parseInt(parametros[1]), Integer.parseInt(parametros[2]),Integer.parseInt(parametros[3]),Float.parseFloat(parametros[4]),Float.parseFloat(parametros[5]),Integer.parseInt(parametros[6]));
                            break;
                    }
                    long inicio = System.currentTimeMillis();
                    al.calcSolucion();
                    long fin = System.currentTimeMillis();
                    logMain.info("\t"+dato.getNombre() + "\t " + al.evaluacion() + "\t\t " +(fin-inicio) + "ms");
                }

            }
            br.close();
            fr.close();
            Log.closeLoggers();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }
}