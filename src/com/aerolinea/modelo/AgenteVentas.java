package com.aerolinea.modelo;
import java.util.ArrayList;
import com.aerolinea.excepciones.TiqueteInvalidoException;

/*HERENCIA: "extends Personal" indica que AgenteVentas HEREDA los atributos y métodos de Personal (relación "es un": un agente ES un personal).
 Como Personal es abstracta, AgenteVentas está obligada a implementar generarReporte(). */

public class AgenteVentas extends Personal {

    // ASOCIACIÓN: el agente conoce/usa los tiquetes que vende.
    private ArrayList<Tiquete> tiquetes;

    // CONSTRUCTOR de la subclase: super(...) llama al constructor de la superclase Personal y debe ser SIEMPRE la primera instrucción.
    public AgenteVentas(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.tiquetes = new ArrayList<>(); // Se inicializa la lista vacía para poder agregar tiquetes después
    }

    //Sobrecarga (polimorfismo estático)
     
    public void venderTiquete(String fecha, Pasajero pasajero) throws TiqueteInvalidoException { //throws: declara 
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La fecha no puede estar vacía.");// "throw" lanza la excepción
        }
        if (pasajero == null) {
            throw new TiqueteInvalidoException("Debe indicarse un pasajero válido.");
        }
        // "this" como argumento: el agente actual queda asociado al tiquete
        Tiquete tiquete = new Tiquete(fecha, "Económica", pasajero, this);
        tiquetes.add(tiquete);
        System.out.println("Tiquete vendido para " + fecha + " a " + pasajero.getNombre());
    }

    //Sobrecarga

    public void venderTiquete(String fecha, String clase, Pasajero pasajero) throws TiqueteInvalidoException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La fecha no puede estar vacía.");
        }
        if (pasajero == null) {
            throw new TiqueteInvalidoException("Debe indicarse un pasajero válido.");
        }
        if (clase == null || clase.trim().isEmpty()) {
            throw new TiqueteInvalidoException("La clase no puede estar vacía.");
        }
        Tiquete tiquete = new Tiquete(fecha, clase, pasajero, this);
        tiquetes.add(tiquete);
        System.out.println("Tiquete vendido para " + fecha + " en clase " + clase + " a " + pasajero.getNombre());
    }

    /*Sobreescritura (Polimorfismo dinámico), @Override le indica al compilador que se está sobreescribiendo.
     Se usan los getters heredados (getNombre, getId, getCargo) porque los atributos de Personal son private y no se pueden usar directamente.*/

    @Override
    public String generarReporte() {
        return "=== AGENTE DE VENTAS ===\nNombre: " + getNombre()
                + "\nID: " + getId()
                + "\nCargo: " + getCargo()
                + "\nTiquetes vendidos: " + tiquetes.size();
    }

    // getter de la lista de tiquetes 
    public ArrayList<Tiquete> getTiquetes() {
        return tiquetes;
    }
}