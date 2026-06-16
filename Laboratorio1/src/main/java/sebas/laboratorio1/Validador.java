/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sebas.laboratorio1;

import java.util.Scanner;

public class Validador {

    // 🔹 Validar texto no vacío
    public static String leerTextoNoVacio(Scanner sc, String mensaje) {
        String dato;

        do {
            System.out.print(mensaje);
            dato = sc.nextLine();

            if (dato.trim().isEmpty()) {
                System.out.println("No puede estar vacío.");
            }

        } while (dato.trim().isEmpty());

        return dato.trim();
    }

    // 🔹 Validar double positivo
    public static double leerDoublePositivo(Scanner sc, String mensaje) {
        double valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Double.parseDouble(sc.nextLine());

                if (valor <= 0) {
                    System.out.println("Debe ser mayor a 0.");
                    continue;
                }

                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    // 🔹 Validar entero en rango
    public static int leerEnteroEnRango(Scanner sc, String mensaje, int min, int max) {
        int valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(sc.nextLine());

                if (valor < min || valor > max) {
                    System.out.println("Fuera de rango (" + min + " - " + max + ").");
                    continue;
                }

                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    // 🔹 Validar entero simple
    public static int leerEntero(Scanner sc, String mensaje) {
        int valor;

        while (true) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(sc.nextLine());
                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}