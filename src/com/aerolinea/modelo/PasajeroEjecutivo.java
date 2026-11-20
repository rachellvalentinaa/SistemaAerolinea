package com.aerolinea.modelo;

//Clase final: impide que otras clases la hereden, ella sí puede heredar de Pasajero.
public final class PasajeroEjecutivo extends Pasajero {

    // ENCAPSULAMIENTO: atributos propios, private
    private int millas;
    private boolean salaVip;

    // CONSTRUCTOR: super(...) para la parte heredada y validación de millas
    public PasajeroEjecutivo(String nombre, String pasaporte, int edad, int millas, boolean salaVip) {
        super(nombre, pasaporte, edad);
        if (millas < 0) {
            throw new IllegalArgumentException("Las millas no pueden ser negativas.");
        }
        this.millas = millas;
        this.salaVip = salaVip;
    }

    //El operador ternario (condición ? valorSi : valorNo) es un if/else.
    @Override
    public String obtenerInfo() {
        return "Pasajero ejecutivo: " + getNombre()
                + " | Pasaporte: " + getPasaporte()
                + " | Edad: " + getEdad()
                + " | Millas: " + millas
                + " | Sala VIP: " + (salaVip ? "Sí" : "No");
    }

    public int getMillas() {
        return millas;
    }
}