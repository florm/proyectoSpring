package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosEscuela;

import javax.persistence.*;

@Entity
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    public Escuela(){}
    public Escuela(DatosEscuela datos){
        this.nombre = datos.getNombre();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
