package com.aerolinea.modelo;

/* Agrupa métodos estáticos de validación que reutilizan las clases (Personal y Pasajero). No se crean objetos de ella, por eso
 su constructor es private: nadie puede hacer "new Validador()". */
public class Validador {

    // CONSTRUCTOR PRIVADO: impide instanciar la clase (ENCAPSULAMIENTO).        
    private Validador() {
    }

    /* static: pertenece a la clase, no a un objeto.
     Se llama directamente: Validador.validarNombre(nombre).Lanza IllegalArgumentException, no obliga a usar try/catch ni throws, 
     pero se puede capturar si se quiere manejar el error. */
    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) { //trim: elimina espacios en blanco al principio y final de cadenas de texto 
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        // Recorre cada carácter del nombre buscando dígitos (ciclo for)
        for (int i = 0; i < nombre.length(); i++) { //length: saber cuántos caracteres tiene una cadena de texto
            if (Character.isDigit(nombre.charAt(i))) { //charAt: obtener el carácter que se encuentra en una posición específica
                throw new IllegalArgumentException("El nombre no puede contener números: " + nombre);
            }
        }
    }

    // MÉTODO ESTÁTICO: valida longitud (6 a 9) y que solo haya letras y dígitos
    public static void validarPasaporte(String pasaporte) {
        if (pasaporte == null || pasaporte.length() < 6 || pasaporte.length() > 9) {
            throw new IllegalArgumentException("El pasaporte debe tener entre 6 y 9 caracteres.");
        }
        // Ciclo for-each: revisa el pasaporte carácter por carácter
        for (char c : pasaporte.toCharArray()) { //char: lenguaje primitivo (A, 1, #), toCharArray: convierte el texto en un arreglo de caracteres. 
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("El pasaporte solo puede tener letras y números.");
            }
        }
    }
}

