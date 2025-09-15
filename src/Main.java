import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Datos d = new Datos("_ejemplo.txt");
        Greedy g = new Greedy(d);
        System.out.println(g.getSolucion());
        System.out.println(d.evaluacion(g.getSolucion()));
    }
}