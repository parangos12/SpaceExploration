package edu.spacexploration.udea.module1;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class EventRandomized {

    // Creacion de los eventos
    public static void main(String[] args) {
        List<Runnable> event = new ArrayList<>();

        event.add(() -> impactoAsteroide());
        event.add(() -> dañosMotores());
        event.add(() -> perdidaCombustible());
        event.add(() -> activationGravedad());
        event.add(() -> falloConexion());
        event.add(() -> falloComunicación());
        event.add(() -> dañoEnegia());
        event.add(() -> mantenimientoMotores());
        event.add(() -> mantenimientoGeneradores());
        event.add(() -> perdidaOxigeno());
        event.add(() -> escasesComida());
        event.add(() -> escasesRepuestos());
        event.add(() -> escasesMedicamentos());
        event.add(() -> heridos());
        event.add(() -> muerteDePersona());

        Random random = new Random();

//Se escoge un evento aleatorio
        int eventoRandom = random.nextInt(event.size());
        event.get(eventoRandom).run();

    }

    // Se realiza las simulaciones de los eventos
    private static void muerteDePersona() {
        System.out.println("Fallecio un pasajero de la nave, se desconocen las causas de la muerte");
    }

    private static void heridos() {
        System.out.println("Por actividades una persona resulto con herida abierta ");
    }

    private static void escasesMedicamentos() {
        System.out.println("Por uso excesivo de los medicamento hay escases de estos");
    }

    private static void escasesRepuestos() {
        System.out.println("Por el gran uso de repasaraciones a diferentes objetos hay falta de respuestos");
    }

    private static void escasesComida() {
        System.out.println("La cantidad de tiempo en la nave los recursos de alimentacion estan escasos");
    }

    private static void perdidaOxigeno() {
        System.out.println("Desconexión de los tuvos de oxigenos, se esta perdiendo presión y cantidad de oxigeno");
    }

    private static void mantenimientoGeneradores() {
        System.out.println("Dia de mantenimiento de los generadores de energia, uso de precausión");
    }

    private static void mantenimientoMotores() {
        System.out.println("Dia de mantenimiento de los motores de la nave");
    }

    private static void dañoEnegia() {
        System.out.println("Perdida de energia, se activa reserva de energia. Hubo daños en los generadores de energia");
    }

    private static void falloComunicación() {
        System.out.println("Fallo en las comunicaciones de la nave no se resive nada de informacion");
    }

    private static void falloConexion() {
        System.out.println("Fallo en la conexion del acople de la nave ");
    }

    private static void activationGravedad() {
        System.out.println("Dia de mantenimiento de los motores de la nave");
    }

    public static void impactoAsteroide() {
        System.out.println("Hubo un impacto en la nave, resulto ser de un asteroide");
    }

    public static void dañosMotores() {
        System.out.println("Daño en los motores principales, resulto por falta de mantenimiento");
    }

    public static void perdidaCombustible() {
        System.out.println("Estamos teniendo perdida de velocidad, falta de combustible en el tanque");
    }
}
// Una manera

//        String[] events = {
//                "impactoAsteroide",
//                "dañosMotores",
//                "perdidaCcombustible",
//                "activacionGravedad",
//                "falloConexion",
//                "falloComunicación",
//                "dañoEnegia",
//                "mantenimientoMotores",
//                "mantenimientoGeneradores",
//                "perdidaOxigeno",
//                "escasesComida",
//                "escasesRepuestos",
//                "escasesMedicamentos",
//                "heridos",
//                "muerteDePersona"
//        };


// Otra manera

                //List<Runnable> events = new ArrayList<>();
                //events.add(() -> System.out.println("Evento 1: Impato de meteorito en la nave."));
                //events.add(() -> System.out.println("Evento 2: Daño en los motores."));
                //events.add(() -> System.out.println("Evento 3: Se activo la gravedad accidentalmente."));
                //events.add(() -> System.out.println("Evento 1: Bienvenido al sistema."));
       // };

