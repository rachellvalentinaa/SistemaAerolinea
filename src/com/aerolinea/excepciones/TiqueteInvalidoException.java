package com.aerolinea.excepciones;

// Se lanza cuando los datos para vender un tiquete no son válidos (fecha vacía, pasajero nulo, clase vacía).
 
public class TiqueteInvalidoException extends Exception {

    // CONSTRUCTOR: recibe el mensaje y lo pasa a la superclase con super(...)
    public TiqueteInvalidoException(String mensaje) {
        super(mensaje);
    }
}