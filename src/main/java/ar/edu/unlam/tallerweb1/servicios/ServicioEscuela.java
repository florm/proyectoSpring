package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.DatosEscuela;
import ar.edu.unlam.tallerweb1.modelo.Escuela;

import java.util.List;

public interface ServicioEscuela {

    Escuela guardar(DatosEscuela datosEscuela);

    List<Escuela> listarTodas();
}
