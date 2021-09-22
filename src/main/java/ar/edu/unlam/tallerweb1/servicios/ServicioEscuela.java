package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Escuela;

import java.util.List;

public interface ServicioEscuela {

    List<Escuela> buscarPor(String nombre);
}
