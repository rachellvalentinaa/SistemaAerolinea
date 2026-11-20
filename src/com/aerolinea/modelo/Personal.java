package com.aerolinea.modelo;

/* Se declara con "abstract", por lo que NO se puede instanciar (no existe "new Personal(...)"). Sirve como molde común para
 AgenteVentas y Piloto, que la heredan. */

public abstract class Personal {

    // Solo se pueden leer o modificar desde esta clase. Las subclases acceden a ellos mediante los getters.
     
    private String nombre;
    private String id;
    private String cargo;

    // CONSTRUCTOR: No retorna aunque la clase es abstracta, el constructor existe porque las subclases lo llaman con super(...). 

    public Personal(String nombre, String id, String cargo) {
        Validador.validarNombre(nombre);
        this.nombre = nombre;
        this.id = id;
        this.cargo = cargo;
    }

    // métodos getters

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    /*MÉTODO ABSTRACTO: solo tiene la firma, NO tiene cuerpo. Obliga a cada subclase a usarlo (SOBREESCRITURA).
     POLIMORFISMO DINÁMICO: cada tipo de personal genera su propio reporte aunque se invoque desde una variable de tipo Personal. */

    public abstract String generarReporte();
}