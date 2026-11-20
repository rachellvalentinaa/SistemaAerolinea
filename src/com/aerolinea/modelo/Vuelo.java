package com.aerolinea.modelo;
import java.util.ArrayList;
import com.aerolinea.excepciones.PasajeroNoEncontradoException;
import com.aerolinea.excepciones.VueloLlenoException;

//Agregación pasajero:Los pasajeros se crean fuera (en App) y se le entregan al vuelo ya construidos.
public class Vuelo {

    private String codigo;
    private String destino;
    private int capacidad;

    private ArrayList<Pasajero> pasajeros;

    public Vuelo(String codigo, String destino, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser mayor a 0.");
        }
        this.codigo = codigo;
        this.destino = destino;
        this.capacidad = capacidad;
        this.pasajeros = new ArrayList<>();
    }

    public void abordar(Pasajero p) throws VueloLlenoException {
        if (pasajeros.size() >= capacidad) {
            throw new VueloLlenoException("Vuelo " + codigo + " sin asientos disponibles.");
        }
        pasajeros.add(p);
        System.out.println("Pasajero " + p.getNombre() + " abordó el vuelo " + codigo);
    }

    public void desembarcar(String pasaporte) throws PasajeroNoEncontradoException {
        Pasajero p = buscarPasajero(pasaporte);
        pasajeros.remove(p);
        System.out.println("Pasajero desembarcado.");
    }

    public Pasajero buscarPasajero(String pasaporte) throws PasajeroNoEncontradoException {
        for (Pasajero p : pasajeros) {
            if (p.getPasaporte().equals(pasaporte)) {
                return p;
            }
        }
        throw new PasajeroNoEncontradoException(pasaporte);
    }

    /*Polimorfismo dinámico: al llamar p.obtenerInfo() Java ejecuta, en tiempo de ejecución, la versión
    de PasajeroEconomico o de PasajeroEjecutivo.*/
    public void listarPasajeros() {
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en este vuelo.");
        } else {
            for (Pasajero p : pasajeros) {
                System.out.println(p.obtenerInfo());
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }
}