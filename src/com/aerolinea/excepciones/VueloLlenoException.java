package com.aerolinea.excepciones;

/* Es una clase propia que HEREDA (extends) de Exception. El compilador obliga a quien use un método que la lance a capturarla (try/catch) o a declararla (throws).
 Se lanza cuando se intenta abordar un vuelo que ya no tiene asientos. */

public class VueloLlenoException extends Exception {

    /* CONSTRUCTOR: recibe el mensaje y lo envía a la superclase con super(...).
     super(...) llama al constructor de Exception, que guarda el mensaje para luego leerlo con getMessage(). */
    public VueloLlenoException(String mensaje) {
        super(mensaje);
    }
}