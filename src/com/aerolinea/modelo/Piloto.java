package com.aerolinea.modelo;

//HERENCIA: Piloto extiende de Personal (un piloto ES un personal).

public class Piloto extends Personal {

    // ENCAPSULAMIENTO: atributo propio de Piloto, private
    private int horasVuelo;

    // CONSTRUCTOR: primero llama a super(...) para inicializar la parte heredada (Personal), valida y asigna el atributo propio.
    public Piloto(String nombre, String id, String cargo, int horasVuelo) {
        super(nombre, id, cargo);
        if (horasVuelo < 0) {
            throw new IllegalArgumentException("Las horas de vuelo no pueden ser negativas.");
        }
        this.horasVuelo = horasVuelo;
    }

    // Sobreescritura (polimorfismo estático): implementa el método abstracto de Personal con el reporte propio del piloto.
     
    @Override
    public String generarReporte() {
        return "=== PILOTO ===\nNombre: " + getNombre()
                + "\nID: " + getId()
                + "\nCargo: " + getCargo()
                + "\nHoras de vuelo: " + horasVuelo;
    }
}