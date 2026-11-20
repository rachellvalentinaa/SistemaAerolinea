package com.aerolinea.modelo;
import java.util.ArrayList;
import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.PersonalNoEncontradoException;

//Composición Vuelo, Si la aerolínea desaparece, sus vuelos también.
public class Aerolinea {

    private String nombre;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Personal> personal;

    //Constructor:  define la composición.
    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new ArrayList<>();
        this.personal = new ArrayList<>();

        vuelos.add(new Vuelo("AV101", "Medellín", 10));
        vuelos.add(new Vuelo("AV202", "Cartagena", 8));
        vuelos.add(new Vuelo("AV303", "Cali", 6));
    }

    public void agregarVuelo(String codigo, String destino, int capacidad) {
        Vuelo vuelo = new Vuelo(codigo, destino, capacidad);
        vuelos.add(vuelo);
        System.out.println("Vuelo '" + codigo + "' a " + destino + " creado con capacidad " + capacidad);
    }

    public void agregarPersonal(Personal p) {
        personal.add(p);
        System.out.println(p.getNombre() + " registrado en la aerolínea.");
    }

    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigo().equals(codigo)) {
                return v;
            }
        }
        return null;
    }

    /* Genérico: busca el pasajero en todos los vuelos, lanzan una excepción si no lo tiene. (try/catch) para
    seguir con el siguiente vuelo. Solo si ninguno lo tiene, se lanza la excepción al final.*/
    public Pasajero buscarPasajero(String pasaporte) throws PasajeroNoEncontradoException {
        for (Vuelo v : vuelos) {
            try {
                return v.buscarPasajero(pasaporte);
            } catch (PasajeroNoEncontradoException e) {
                // No está en este vuelo: se continúa con el siguiente
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    // CAST: (AgenteVentas) convierte la referencia de Personal a AgenteVentas, necesario para devolver el tipo específico.
    public AgenteVentas buscarAgente(String id) throws PersonalNoEncontradoException {
        for (Personal p : personal) {
            if (p instanceof AgenteVentas && p.getId().equals(id)) { //instanceof: verifica el tipo REAL del objeto en tiempo de ejecución.
                return (AgenteVentas) p;
            }
        }
        throw new PersonalNoEncontradoException(id);
    }

    // MÉTODO GENÉRICO: imprime solo los agentes del personal real registrado
    public void listarAgentes() {
        System.out.println("Agentes disponibles:");
        for (Personal p : personal) {
            if (p instanceof AgenteVentas) {
                System.out.println(p.getId() + " - " + p.getNombre() + " (" + p.getCargo() + ")");
            }
        }
    }

    //Polimorfismo dinámico: p.generarReporte() ejecuta la versión de AgenteVentas o de Piloto.
    public void generarReporteGeneral() {
        System.out.println("=== REPORTE GENERAL: " + nombre + " ===");
        for (Vuelo v : vuelos) {
            System.out.println("Vuelo: " + v.getCodigo()
                    + " | Destino: " + v.getDestino()
                    + " | Ocupación: " + v.getPasajeros().size() + "/" + v.getCapacidad());
        }
        System.out.println("Total de personal: " + personal.size());
        for (Personal p : personal) {
            System.out.println(p.generarReporte());
        }
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public ArrayList<Personal> getPersonal() {
        return personal;
    }
}