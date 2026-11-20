package com.aerolinea.modelo;

/*Clase abstracta: no se puede instanciar directamente. Define lo común de todo pasajero y obliga a las subclases
(PasajeroEconomico y PasajeroEjecutivo) a implementar obtenerInfo(). */
public abstract class Pasajero {

    private String nombre;
    private String pasaporte;
    private int edad;

    //CONSTRUCTOR: valida los datos antes de asignarlos.
    public Pasajero(String nombre, String pasaporte, int edad) {
        Validador.validarNombre(nombre);
        Validador.validarPasaporte(pasaporte);
        if (edad < 0 || edad > 120) {
            throw new IllegalArgumentException("Edad inválida: " + edad);
        }
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public int getEdad() {
        return edad;
    }

    //ABSTRACTO: sin cuerpo. Cada tipo de pasajero lo implementa a su manera, (SOBREESCRITURA, POLIMORFISMO DINÁMICO)     
    public abstract String obtenerInfo();
}