package ar.edu.unlam.tallerweb1.controladores;

public class DatosEscuela {

    private String nombre;

    public DatosEscuela(){}
    public DatosEscuela(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
