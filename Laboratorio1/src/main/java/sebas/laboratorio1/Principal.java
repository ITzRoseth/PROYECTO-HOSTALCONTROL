/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.laboratorio1;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Competencia c = new Competencia();

        int op;

        do {
            System.out.println("\n========== MENU DE OPCIONES ==========");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Campeon");
            System.out.println("4. Por pais");
            System.out.println("5. Promedio");
            System.out.println("6. Eliminar");
            System.out.println("7. Salir");

            op = Validador.leerEnteroEnRango(sc, "Opcion: ", 1, 7);

            switch (op) {

                case 1:
                    String nombre = Validador.leerTextoNoVacio(sc, "Nombre: ");
                    String pais = Validador.leerTextoNoVacio(sc, "Pais: ");
                    double tiempo = Validador.leerDoublePositivo(sc, "Tiempo: ");
                    int pos = Validador.leerEnteroEnRango(sc, "Posicion: ", 0, c.size());

                    try {
                        c.registrar(new Atleta(nombre, pais, tiempo), pos);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    c.mostrar();
                    break;

                case 3:
                    c.campeon();
                    break;

                case 4:
                    String p = Validador.leerTextoNoVacio(sc, "Pais: ");
                    c.porPais(p);
                    break;

                case 5:
                    c.promedio();
                    break;

                case 6:
                    c.mostrar();
                    int idx = Validador.leerEntero(sc, "Indice: ");
                    c.eliminar(idx);
                    break;
            }

        } while (op != 7);

        sc.close();
    }
}

