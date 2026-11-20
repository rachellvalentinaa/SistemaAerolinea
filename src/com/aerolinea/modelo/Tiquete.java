package com.aerolinea.modelo;

/*Asociación: Tiquete relaciona a un AgenteVentas con un Pasajero. Guarda referencias a ambos objetos, pero ninguno depende de la
existencia del tiquete */
public class Tiquete {

    private String fecha;
    private String clase;
    private Pasajero pasajero;      // asociación con Pasajero
    private AgenteVentas agente;    // asociación con AgenteVentas

    //Recibe el tipo abstracto Pasajero, así que acepta cualquier subtipo (económico o ejecutivo).
    public Tiquete(String fecha, String clase, Pasajero pasajero, AgenteVentas agente) {
        this.fecha = fecha;
        this.clase = clase;
        this.pasajero = pasajero;
        this.agente = agente;
    }

    public String getInfo() {
        return "Tiquete: " + fecha
                + " | Clase: " + clase
                + " | Agente: " + agente.getNombre()
                + " | Pasajero: " + pasajero.getNombre();
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public AgenteVentas getAgente() {
        return agente;
    }

    public String getFecha() {
        return fecha;
    }
}