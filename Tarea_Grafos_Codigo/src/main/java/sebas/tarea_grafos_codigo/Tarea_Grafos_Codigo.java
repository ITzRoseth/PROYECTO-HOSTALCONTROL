/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package sebas.tarea_grafos_codigo;

import java.util.*;

/**
 * Implementación del Algoritmo de Dijkstra para encontrar el camino más corto
 * desde un nodo origen hacia todos los demás nodos en un grafo con pesos positivos.
 */
public class Tarea_Grafos_Codigo {

    // Clase interna para representar una arista (conexión hacia un nodo con un peso)
    static class Arista {
        int destino;
        int peso;

        public Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Clase interna para manejar los nodos en la Cola de Prioridad
    static class NodoPrioridad implements Comparable<NodoPrioridad> {
        int nodo;
        int distancia;

        public NodoPrioridad(int nodo, int distancia) {
            this.nodo = nodo;
            this.distancia = distancia;
        }

        // Comparamos por distancia para que la Cola de Prioridad ordene de menor a mayor
        @Override
        public int compareTo(NodoPrioridad otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }

    // Clase que representa el Grafo usando una lista de adyacencia
    static class Grafo {
        int numNodos;
        List<List<Arista>> listaAdyacencia;

        public Grafo(int numNodos) {
            this.numNodos = numNodos;
            listaAdyacencia = new ArrayList<>(numNodos);
            for (int i = 0; i < numNodos; i++) {
                listaAdyacencia.add(new ArrayList<>());
            }
        }

        // Método para agregar una arista dirigida
        // Para grafos no dirigidos, se debe llamar a este método en ambas direcciones
        public void agregarArista(int origen, int destino, int peso) {
            listaAdyacencia.get(origen).add(new Arista(destino, peso));
        }
    }

    /**
     * Método principal que ejecuta el algoritmo de Dijkstra.
     * @param grafo El grafo sobre el cual operar.
     * @param nodoOrigen El nodo de inicio.
     */
    public static void ejecutarDijkstra(Grafo grafo, int nodoOrigen) {
        int numNodos = grafo.numNodos;
        
        // Arreglo para almacenar las distancias mínimas desde el origen
        int[] distancias = new int[numNodos];
        // Arreglo para reconstruir el camino (almacena el nodo anterior)
        int[] previos = new int[numNodos];
        
        // Inicializamos todas las distancias como "infinito"
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(previos, -1);
        
        // La distancia del nodo origen a sí mismo es 0
        distancias[nodoOrigen] = 0;

        // Cola de prioridad para procesar siempre el nodo más cercano primero
        PriorityQueue<NodoPrioridad> colaPrioridad = new PriorityQueue<>();
        colaPrioridad.add(new NodoPrioridad(nodoOrigen, 0));

        while (!colaPrioridad.isEmpty()) {
            // Extraemos el nodo con la distancia mínima actual
            NodoPrioridad actual = colaPrioridad.poll();
            int u = actual.nodo;

            // Si encontramos una distancia mayor en la cola que la ya registrada, la ignoramos
            if (actual.distancia > distancias[u]) {
                continue;
            }

            // Exploramos todos los vecinos del nodo actual
            for (Arista vecino : grafo.listaAdyacencia.get(u)) {
                int v = vecino.destino;
                int pesoArista = vecino.peso;

                // Relajación: Verificamos si podemos mejorar la distancia hacia el vecino 'v'
                // pasando a través del nodo 'u'
                if (distancias[u] + pesoArista < distancias[v]) {
                    distancias[v] = distancias[u] + pesoArista;
                    previos[v] = u; // Guardamos de dónde venimos para reconstruir la ruta
                    colaPrioridad.add(new NodoPrioridad(v, distancias[v]));
                }
            }
        }

        // Imprimir los resultados
        imprimirResultados(nodoOrigen, distancias, previos);
    }

    /**
     * Método auxiliar para mostrar las distancias y los caminos en consola.
     */
    private static void imprimirResultados(int nodoOrigen, int[] distancias, int[] previos) {
        System.out.println("Resultados del Algoritmo de Dijkstra (Origen: Nodo " + nodoOrigen + "):");
        System.out.println("===============================================================");
        System.out.printf("%-10s %-15s %-30s\n", "Destino", "Dist. Mínima", "Ruta");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < distancias.length; i++) {
            if (i == nodoOrigen) continue; // Saltamos el nodo origen

            System.out.printf("Nodo %-5d ", i);
            if (distancias[i] == Integer.MAX_VALUE) {
                System.out.printf("%-15s %-30s\n", "Inalcanzable", "Ninguna");
            } else {
                System.out.printf("%-15d %-30s\n", distancias[i], obtenerRuta(i, previos));
            }
        }
    }

    /**
     * Reconstruye la ruta desde el origen hasta el destino usando el arreglo 'previos'.
     */
    private static String obtenerRuta(int destino, int[] previos) {
        List<Integer> ruta = new ArrayList<>();
        for (int at = destino; at != -1; at = previos[at]) {
            ruta.add(at);
        }
        Collections.reverse(ruta);
        return ruta.toString().replace("[", "").replace("]", "").replace(", ", " -> ");
    }

    // --- MÉTODO MAIN PARA PROBAR EL ALGORITMO ---
    public static void main(String[] args) {
        // Creamos un grafo con 5 nodos (0 a 4)
        int numNodos = 5;
        Grafo grafo = new Grafo(numNodos);

        // Agregamos las aristas (Origen, Destino, Peso)
        // Ejemplo de un grafo dirigido pesado
        grafo.agregarArista(0, 1, 4);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(2, 1, 2);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 5);
        grafo.agregarArista(3, 4, 3);
        
        System.out.println("Grafo inicializado.");
        
        // Ejecutamos Dijkstra partiendo desde el nodo 0
        ejecutarDijkstra(grafo, 0);
    }
}

