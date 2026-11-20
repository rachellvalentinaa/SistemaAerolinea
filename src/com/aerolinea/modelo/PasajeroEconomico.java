package com.aerolinea.modelo;

//HERENCIA: PasajeroEconomico extiende de Pasajero (ES un pasajero).
public class PasajeroEconomico extends Pasajero {
    // ENCAPSULAMIENTO
    private int equipajeKg;

    public PasajeroEconomico(String nombre, String pasaporte, int edad, int equipajeKg) {
        super(nombre, pasaporte, edad);
        if (equipajeKg < 0 || equipajeKg > 23) {
            throw new IllegalArgumentException("El equipaje debe estar entre 0 y 23 kg.");
        }
        this.equipajeKg = equipajeKg;
    }

    //Sobreescritura, (POLIMORFISMO DINÁMICO): implementa obtenerInfo()con el formato propio del pasajero económico. Usa los getters heredados.
     
    @Override
    public String obtenerInfo() {
        return "Pasajero económico: " + getNombre()
                + " | Pasaporte: " + getPasaporte()
                + " | Edad: " + getEdad()
                + " | Equipaje: " + equipajeKg + " kg";
    }
}