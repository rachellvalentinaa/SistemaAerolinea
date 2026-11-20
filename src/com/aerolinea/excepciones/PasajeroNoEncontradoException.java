package com.aerolinea.excepciones;

// Se lanza cuando se busca un pasajero por pasaporte y no existe. Hereda de Exception (HERENCIA).

public class PasajeroNoEncontradoException extends Exception {

    // CONSTRUCTOR: recibe el pasaporte (PARÁMETRO) y construye el mensaje que se pasa a la superclase con super(...). 
    
    public PasajeroNoEncontradoException(String pasaporte) {
        super("Pasajero con pasaporte " + pasaporte + " no encontrado.");
    }
}