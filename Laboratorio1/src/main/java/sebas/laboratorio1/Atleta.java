/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.laboratorio1;

/**
 *
 * @author sebas
 */
public class Atleta {
    private String nombre;
    private String nacionalidad;
    private double tiempo;

    public Atleta(String nombre, String nacionalidad, double tiempo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre invalido.");
        }
        if (nacionalidad == null || nacionalidad.trim().isEmpty()) {
            throw new IllegalArgumentException("Nacionalidad invalida.");
        }
        if (tiempo <= 0) {
            throw new IllegalArgumentException("Tiempo invalido.");
        }

        this.nombre = nombre.trim();
        this.nacionalidad = nacionalidad.trim();
        this.tiempo = tiempo;
    }

    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public double getTiempo() { return tiempo; }

    @Override
    public String toString() {
        return nombre + " | " + nacionalidad + " | " + tiempo;
    }

    public String toFile() {
        return nombre + ";" + nacionalidad + ";" + tiempo;
    }

    public static Atleta fromFile(String linea) {
        try {
            String[] p = linea.split(";");
            return new Atleta(p[0], p[1], Double.parseDouble(p[2]));
        } catch (Exception e) {
            return null;
        }
    }
}