import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
// EYDER DE JESUS ARROYO VEGA- ANDRES FELIPE PARDO BENAVIDES
public class SimuladordeViajeInterplanetario {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Inicializar las listas de planetas y sus distancias desde la Tierra en
        // millones
        // de KM
        List<String> planets = new ArrayList<>(
                Arrays.asList("Mercurio", "Venus", "Tierra", "Marte", "Júpiter", "Saturno", "Urano", "Neptuno"));
        List<Double> distances = new ArrayList<>(Arrays.asList(91.7, 42.4, 0.0, 78.3, 628.9, 1284.4, 2721.0, 43347.0));

        // Inicializar los arrays de naves espaciales, sus velocidades y capacidades
        String[] ships = { "Nave Corporation Capsule", "Nave kompany", "Nave Halcon" };
        double[] shipSpeeds = { 50000, 75000, 100000 };
        int[] shipCapacities = { 100, 200, 300 };

        // Mostrar mensaje de bienvenida al usuario al momento ejecutar el programa
        System.out.println("========================================");
        System.out.println("Bienvenido al viaje interplanetario");
        System.out.println("========================================");

        // Variable para controlar si el usuario desea continuar viajando
        boolean continueTravel = true;

        while (continueTravel) {
            int choice = -1;
            // Solicitar al usuario que seleccione un planeta de destino
            while (choice < 1 || choice > planets.size()) {
                System.out.println("Seleccione su destino interplanetario:");
                for (int i = 0; i < planets.size(); i++) {
                    System.out.println((i + 1) + ". " + planets.get(i));
                }
                choice = scanner.nextInt();
                if (choice < 1 || choice > planets.size()) {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }

            // Obtener la distancia al planeta seleccionado
            double distance = distances.get(choice - 1);
            System.out.println(
                    "Distancia desde el planeta Tierra a tu planeta de destino: " + distance + " millones de KM");

            int shipChoice = -1;
            // Solicitar al usuario que seleccione una nave espacial
            while (shipChoice < 1 || shipChoice > ships.length) {
                System.out.println("Seleccione su nave espacial:");
                for (int i = 0; i < ships.length; i++) {
                    System.out.println((i + 1) + ". " + ships[i] + " (Velocidad máxima: " + shipSpeeds[i]
                            + " km/h, Capacidad: " + shipCapacities[i] + " pasajeros)");
                }
                shipChoice = scanner.nextInt();
                if (shipChoice < 1 || shipChoice > ships.length) {
                    System.out.println("La nave seleccionada no está disponible. Elige la nave correspondiente.");
                }
            }

            // Obtener la velocidad y capacidad de la nave seleccionada para el viaje
            double speed = shipSpeeds[shipChoice - 1];
            int capacity = shipCapacities[shipChoice - 1];
            System.out.println("=================================================");
            System.out.println("Ingresar la cantidad de pasajeros a abordar:");
            System.out.println("==================================================");
            int passengers = scanner.nextInt();
            if (passengers <= 0) {
                System.out.println("La cantidad de pasajeros debe ser un valor positivo.");
                return;
            } else if (passengers > capacity) {
                System.out.println("Error: La cantidad de pasajeros supera la capacidad máxima de la nave.");
                return;
            }
            // ----------------------------------------------------------------------------------------------------------------
            // Calcular el tiempo estimado de viaje en días
            double timeInHours = (distance * 1_000_000) / speed;
            double timeInDays = timeInHours / 24;
            System.out.println("El tiempo estimado de viaje es: " + timeInDays + " días");

            // Calcular el consumo total de combustible y oxígeno
            double fuelConsumptionPerKm = 0.5;
            double oxygenConsumptionPerHour = 0.8;

            double totalFuel = distance * 1_000_000 * fuelConsumptionPerKm;
            double totalOxygen = timeInHours * oxygenConsumptionPerHour;

            System.out.println("Cantidad de combustible necesario: " + totalFuel + " L");
            System.out.println("Cantidad de oxígeno necesario: " + totalOxygen + " L");

            // Preguntar si el usuario desea ajustar algunos recursos antes de partir
            System.out.println("Desea ajustar los recursos antes de partir (SI/NO)?");
            String adjustResources = scanner.next();

            if (adjustResources.equalsIgnoreCase("SI")) {
                System.out.println("Ingrese la cantidad de combustible (L):");
                totalFuel = scanner.nextDouble();

                System.out.println("Ingrese la cantidad de oxígeno (L):");
                totalOxygen = scanner.nextDouble();
            }
            System.out.println("=======================");
            System.out.println("Ajustes de recursos:");
            System.out.println("=======================");
            System.out.println("Combustible: " + totalFuel + " L");
            System.out.println("Oxígeno: " + totalOxygen + " L");

            // Simulación del progreso del viaje en porcentaje de 0% a 100%
            System.out.println("Inicio del viaje...");
            for (int i = 0; i <= 100; i += 10) {
                System.out.println("Progreso del viaje: " + i + "% completado.");
                if (i == 50) {
                    System.out.println("Mitad del camino alcanzada.");
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Has llegado a tu destino. ¡Bienvenido a " + planets.get(choice - 1) + "!");

            // se le pregunta al usuario, si desea realizar otro viaje
            System.out.println("=========================================");
            System.out.println("¿Desea viajar a otro planeta? (SI/NO)");
            System.out.println("=========================================");
            String anotherTrip = scanner.next();
            if (!anotherTrip.equalsIgnoreCase("SI")) {
                continueTravel = false;
            }
        }

        // Cerrar el escáner para la finalizacion del programa
        scanner.close();
    }
}
