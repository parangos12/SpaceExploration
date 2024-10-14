package edu.spacexploration.udea.module1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventRandomized2 {

    public static void main(String[] args) {
        // Crear un conjunto de datos (pueden ser cadenas de texto en este caso.(Hablar con los chicos))
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            lista.add("Cosa" + i);
        }

        // Lista de eventos
        List<String> events = new ArrayList<>();
        events.add("Fallas en el motor principal");
        events.add("Choque con Asteroide");
        events.add("Perdida de combustible");
        events.add("Fallas de comunicacion");
        events.add("Falla en la energia");
        events.add("Muerte de un pasajero");

        // Seleccionar un evento aleatorio
        Random random = new Random();
        String Seleccion = events.get(random.nextInt(events.size()));

        // Simular el evento seleccionado
        System.out.println("Evento escogido: " + Seleccion);
        lista = simulador(lista, Seleccion);

        // Mostrar el conjunto de datos resultante
        System.out.println("Conjunto de datos después del evento: " + lista);
    }

    // Las simulaciones de los eventos
    public static List<String> simulador(List<String> lista, String events) {
        Random random = new Random();

        switch (events) {
            case "Fallas en el motor principal":
                // Introduce un pequeño retraso (simulado con un bucle)
                try {
                    Thread.sleep(5000); // Pausa de 5 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "Choque con Asteroide":
                // El 40% de los datos se agrega un indice que dice dañado
                for (int i = 0; i < lista.size(); i++) {
                    if (random.nextDouble() < 0.4) {
                        lista.set(i, lista.get(i) + " - Dañado");
                    }
                }
                break;
            case "Perdida de combustible":
                // Se eliminan datos (se quitan un 33%)
                lista = new ArrayList<>(lista.subList(0, lista.size() / 3));
                break;

            case "Fallas de comunicacion":
                // Se elimina la mitad y se rompen el 20% de los datos
                lista = new ArrayList<>(lista.subList(0, lista.size() / 3));
                for (int i = 0; i < lista.size(); i++) {
                    if (random.nextDouble() < 0.2) {
                        lista.set(i, lista.get(i) + "rompen");
                    }
                }
                break;

            case "Falla de energia":
                // Elimina la mitad de los datos
                lista = new ArrayList<>(lista.subList(0, lista.size() / 2));
                break;

            case "Muerte de un pasajero":
                // Eliminar un dato aleatorio si hay al menos uno en la lista
                if (!lista.isEmpty()) {
                    int indiceEliminar = random.nextInt(lista.size());
                    lista.remove(indiceEliminar);
                    System.out.println("Un pasajero ha muerto, eliminando el dato en la posición: " + indiceEliminar);
                }
                break;

            default:
                System.out.println("Evento desconocido.");
        }

        return lista;
    }
}

