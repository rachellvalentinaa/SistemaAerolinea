package com.aerolinea.app;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;
import com.aerolinea.excepciones.TiqueteInvalidoException;
import com.aerolinea.excepciones.VueloLlenoException;
import com.aerolinea.modelo.AgenteVentas;
import com.aerolinea.modelo.Aerolinea;
import com.aerolinea.modelo.Pasajero;
import com.aerolinea.modelo.PasajeroEconomico;
import com.aerolinea.modelo.PasajeroEjecutivo;
import com.aerolinea.modelo.Piloto;
import com.aerolinea.modelo.Tiquete;
import com.aerolinea.modelo.Vuelo;

public class App {
    /*Auxiliar estático: insiste hasta recibir un entero válido. InputMismatchException (se lanza cuando se escribe texto en
    vez de un número) y limpia el buffer (memoria temporal) con sc.nextLine() para no quedar en un ciclo infinito.*/
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // limpia el salto de línea que deja nextInt()
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número entero.");
                sc.nextLine(); // descarta la entrada inválida
            }
        }
    }

    //Auxiliar estático: busca un pasajero en la lista temporal de registrados (los que aún no han abordado).
    private static Pasajero buscarRegistrado(ArrayList<Pasajero> lista, String pasaporte) {
        for (Pasajero p : lista) {
            if (p.getPasaporte().equals(pasaporte)) {
                return p;
            }
        }
        return null;
    }

    //main: punto de entrada del programa. Es static para se pueda ejecutar sin crear un objeto de App.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Pasajero> pasajerosRegistrados = new ArrayList<>();
        // OBJETO: instancia de la clase Aerolinea (crea sus vuelos por composición)
        Aerolinea aerolinea = new Aerolinea("Aerolínea Andes Express");
        // OBJETOS de las subclases, creados con sus constructores (argumentos)
        AgenteVentas a1 = new AgenteVentas("Sofía Herrera", "AG001", "Ventas aeropuerto");
        AgenteVentas a2 = new AgenteVentas("Mateo Vargas", "AG002", "Ventas en línea");
        Piloto p1 = new Piloto("Capitán Ricardo León", "P001", "Piloto comandante", 5200);

        //agregarPersonal recibe un Personal, pero se le pasan AgenteVentas y Piloto (subclases)
        aerolinea.agregarPersonal(a1);
        aerolinea.agregarPersonal(a2);
        aerolinea.agregarPersonal(p1);

        int opcion;
        // REPETITIVA do-while: el menú se muestra al menos una vez
        do {
            System.out.println("\n=== MENÚ AEROLÍNEA ===");
            System.out.println("1. Registrar pasajero");
            System.out.println("2. Listar pasajeros registrados");
            System.out.println("3. Abordar pasajero a un vuelo");
            System.out.println("4. Vender tiquete");
            System.out.println("5. Ver pasajeros de un vuelo");
            System.out.println("6. Ver tiquetes de un agente");
            System.out.println("7. Desembarcar un pasajero");
            System.out.println("8. Buscar pasajero por pasaporte");
            System.out.println("9. Reporte general de la aerolínea");
            System.out.println("10. Crear nuevo vuelo");
            System.out.println("0. Salir");
            opcion = leerEntero(sc, "Opción: ");

            /*CONDICIONAL switch: elige el bloque según la opción. Cada case lleva llaves { } para que las variables declaradas
            dentro tengan su propio alcance y no choquen con las de otros case.*/
            switch (opcion) {

                case 1: {
                    int tipo = leerEntero(sc, "Tipo de pasajero (1=Económico, 2=Ejecutivo): ");
                    if (tipo != 1 && tipo != 2) {
                        System.out.println("Tipo inválido.");
                        break;
                    }
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Pasaporte: ");
                    String pasaporte = sc.nextLine();
                    int edad = leerEntero(sc, "Edad: ");

                    /* try/catch: si algún constructor lanza IllegalArgumentException(dato inválido), se captura aquí y se muestra el mensaje
                    en lugar de que el programa se detenga.*/
                    try {
                        if (tipo == 1) {
                            int equipaje = leerEntero(sc, "Equipaje (kg): ");
                            pasajerosRegistrados.add(new PasajeroEconomico(nombre, pasaporte, edad, equipaje));
                        } else {
                            int millas = leerEntero(sc, "Millas: ");
                            System.out.print("¿Tiene acceso a sala VIP? (s/n): ");
                            boolean salaVip = sc.nextLine().trim().equalsIgnoreCase("s");
                            pasajerosRegistrados.add(new PasajeroEjecutivo(nombre, pasaporte, edad, millas, salaVip));
                        }
                        System.out.println("Pasajero registrado correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    if (pasajerosRegistrados.isEmpty()) {
                        System.out.println("No hay pasajeros registrados.");
                    } else {
                        // POLIMORFISMO DINÁMICO: obtenerInfo() se ejecuta según el tipo real
                        for (Pasajero p : pasajerosRegistrados) {
                            System.out.println(p.obtenerInfo());
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.print("Código del vuelo: ");
                    String codigo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codigo);
                    if (vuelo == null) {
                        System.out.println("Error: el vuelo no existe.");
                        break;
                    }
                    System.out.print("Pasaporte del pasajero: ");
                    String pasaporte = sc.nextLine();
                    Pasajero pasajero = buscarRegistrado(pasajerosRegistrados, pasaporte);
                    if (pasajero == null) {
                        System.out.println("Error: el pasajero no está registrado.");
                        break;
                    }
                    try {
                        vuelo.abordar(pasajero);
                    } catch (VueloLlenoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 4: {
                    aerolinea.listarAgentes();
                    System.out.print("ID del agente: ");
                    String idAgente = sc.nextLine();
                    try {
                        AgenteVentas agente = aerolinea.buscarAgente(idAgente);
                        System.out.print("Pasaporte del pasajero: ");
                        String pasaporte = sc.nextLine();
                        // Puede lanzar PasajeroNoEncontradoException
                        Pasajero pasajero = aerolinea.buscarPasajero(pasaporte);
                        System.out.print("Fecha: ");
                        String fecha = sc.nextLine();
                        System.out.print("¿Desea indicar la clase? (s/n): ");
                        String conClase = sc.nextLine();

                        //SOBRECARGA: según los argumentos, Java elige la versión de venderTiquete (2 o 3 parámetros).
                        if (conClase.trim().equalsIgnoreCase("s")) {
                            System.out.print("Clase: ");
                            String clase = sc.nextLine();
                            agente.venderTiquete(fecha, clase, pasajero);
                        } else {
                            agente.venderTiquete(fecha, pasajero);
                        }
                    } catch (PersonalNoEncontradoException | PasajeroNoEncontradoException | TiqueteInvalidoException e) {
                        // Multi-catch: un solo bloque para varias excepciones
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 5: {
                    System.out.print("Código del vuelo: ");
                    String codigo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codigo);
                    if (vuelo == null) {
                        System.out.println("Error: el vuelo no existe.");
                    } else {
                        vuelo.listarPasajeros();
                    }
                    break;
                }

                case 6: {
                    aerolinea.listarAgentes();
                    System.out.print("ID del agente: ");
                    String idAgente = sc.nextLine();
                    try {
                        AgenteVentas agente = aerolinea.buscarAgente(idAgente);
                        if (agente.getTiquetes().isEmpty()) {
                            System.out.println("El agente no ha vendido tiquetes.");
                        } else {
                            for (Tiquete t : agente.getTiquetes()) {
                                System.out.println(t.getInfo());
                            }
                        }
                    } catch (PersonalNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 7: {
                    System.out.print("Código del vuelo: ");
                    String codigo = sc.nextLine();
                    Vuelo vuelo = aerolinea.buscarVuelo(codigo);
                    if (vuelo == null) {
                        System.out.println("Error: el vuelo no existe.");
                        break;
                    }
                    System.out.print("Pasaporte del pasajero: ");
                    String pasaporte = sc.nextLine();
                    try {
                        vuelo.desembarcar(pasaporte);
                    } catch (PasajeroNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 8: {
                    System.out.print("Pasaporte a buscar: ");
                    String pasaporte = sc.nextLine();
                    try {
                        Pasajero pasajero = aerolinea.buscarPasajero(pasaporte);
                        System.out.println(pasajero.obtenerInfo());
                    } catch (PasajeroNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 9: {
                    aerolinea.generarReporteGeneral();
                    break;
                }

                case 10: {
                    System.out.print("Código del nuevo vuelo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    int capacidad = leerEntero(sc, "Capacidad: ");
                    try {
                        aerolinea.agregarVuelo(codigo, destino, capacidad);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 0: {
                    System.out.println("Programa terminado.");
                    break;
                }

                default: {
                    System.out.println("Opción no válida.");
                    break;
                }
            }

        } while (opcion != 0);

        sc.close();
    }
}