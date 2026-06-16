package sebas.laboratorio1;
import java.util.ArrayList;
import java.io.*;

public class Competencia {
    private ArrayList<Atleta> atletas;
    private final String archivo = "atletas.txt";

    public Competencia() {
        atletas = new ArrayList<>();
        cargar();
    }

    public void registrar(Atleta a, int pos) {
        if (existe(a.getNombre())) {
            System.out.println("Atleta duplicado.");
            return;
        }

        if (pos < 0 || pos > atletas.size()) {
            atletas.add(a);
        } else {
            atletas.add(pos, a);
        }

        guardar();
    }

    private boolean existe(String nombre) {
        for (Atleta a : atletas) {
            if (a.getNombre().equalsIgnoreCase(nombre)) return true;
        }
        return false;
    }

    public void eliminar(int pos) {
        if (atletas.isEmpty()) {
            System.out.println("Lista vacía.");
            return;
        }

        if (pos >= 0 && pos < atletas.size()) {
            atletas.remove(pos);
            System.out.println("Removido Exitosamente!");
            guardar();
        } else {
            System.out.println("Indice invalido.");
        }
    }

    public void mostrar() {
        if (atletas.isEmpty()) {
            System.out.println("No hay datos.");
            return;
        }

        for (int i = 0; i < atletas.size(); i++) {
            System.out.println("[" + i + "] " + atletas.get(i));
        }
    }

    public void campeon() {
        if (atletas.isEmpty()) return;

        Atleta best = atletas.get(0);
        for (Atleta a : atletas) {
            if (a.getTiempo() < best.getTiempo()) best = a;
        }

        System.out.println("Campeon: " + best);
    }

    public void promedio() {
        if (atletas.isEmpty()) return;

        double suma = 0;
        for (Atleta a : atletas) suma += a.getTiempo();

        System.out.println("Promedio: " + (suma / atletas.size()));
    }

    public void porPais(String pais) {
        boolean ok = false;

        for (Atleta a : atletas) {
            if (a.getNacionalidad().equalsIgnoreCase(pais)) {
                System.out.println(a);
                ok = true;
            }
        }

        if (!ok) System.out.println("Sin resultados.");
    }

    private void guardar() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Atleta a : atletas) {
                bw.write(a.toFile());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando.");
        }
    }

    private void cargar() {
        File f = new File(archivo);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                Atleta a = Atleta.fromFile(linea);
                if (a != null) atletas.add(a);
            }

        } catch (IOException e) {
            System.out.println("Error cargando.");
        }
    }

    public int size() {
        return atletas.size();
    }
}