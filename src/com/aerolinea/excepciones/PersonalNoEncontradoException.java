package com.aerolinea.excepciones;

// Se lanza cuando se busca un miembro del personal por id y no existe.

public class PersonalNoEncontradoException extends Exception {

    // CONSTRUCTOR: arma el mensaje con el id y lo envía a Exception con super(...)

    public PersonalNoEncontradoException(String id) {
        super("No existe personal registrado con id: " + id + ".");
    }
}