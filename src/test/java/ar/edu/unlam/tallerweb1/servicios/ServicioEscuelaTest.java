package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.controladores.ControladorEscuela;
import ar.edu.unlam.tallerweb1.controladores.DatosEscuela;
import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioEscuela;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class ServicioEscuelaTest {


    private static final String NOMBRE = "Pestalozzi";

    private RepositorioEscuela repositorioEscuela = mock(RepositorioEscuela.class);
    private ServicioEscuela servicioEscuela = new ServicioEscuelaImpl(repositorioEscuela);

    @Test
    public void siLaEscuelaContieneNombreSeGuardaCorrectamente(){
        DatosEscuela datosEscuela = givenExisteDatoDeEscuela(NOMBRE);
        Escuela escuelaGuardada = whenCreoEscuela(datosEscuela);
        thenSeGuardaCorrectamente(escuelaGuardada);
    }

    @Test(expected = CampoVacioException.class)
    public void SiLaEscuelaNoTieneNombreFalla(){
        DatosEscuela datosEscuela = givenDatoDeEscuelaSinNombre();
        Escuela escuelaGuardada = whenCreoEscuela(datosEscuela);
        thenFallaLaCreacion(escuelaGuardada);

    }

    @Test
    public void sePuedenListarTodasLasEscuelas(){
        List<Escuela> escuelas = givenExisteEscuelas(NOMBRE,3);
        when(repositorioEscuela.buscarTodas()).thenReturn(escuelas);

        List<Escuela> escuelasBuscadas = whenBuscoTodasLasEscuelas();
        Integer cantidadEsperada = 3;
        thenObtengoEscuelas(escuelasBuscadas, cantidadEsperada);
    }

    private void thenObtengoEscuelas(List<Escuela> escuelasBuscadas, Integer cantidadEsperada) {
        assertThat(escuelasBuscadas).hasSize(cantidadEsperada);
    }

    private List<Escuela> whenBuscoTodasLasEscuelas() {
        return servicioEscuela.listarTodas();
    }

    private List<Escuela> givenExisteEscuelas(String nombre, Integer cantidadEscuelas) {
        List<Escuela> escuelas = new ArrayList<>();
        for (int i = 0; i<cantidadEscuelas; i++){
            escuelas.add(new Escuela(new DatosEscuela(nombre+i)));
        }
        return escuelas;
    }

    private void thenFallaLaCreacion(Escuela escuelaGuardada) {
        verify(repositorioEscuela, times(0)).guardar(escuelaGuardada);
    }

    private DatosEscuela givenDatoDeEscuelaSinNombre() {
        return new DatosEscuela();
    }

    private void thenSeGuardaCorrectamente(Escuela escuela) {
       verify(repositorioEscuela).guardar(escuela);
    }

    private Escuela whenCreoEscuela(DatosEscuela datosEscuela) {
        Escuela escuelaNueva = servicioEscuela.guardar(datosEscuela);
        return escuelaNueva;
    }

    private DatosEscuela givenExisteDatoDeEscuela(String nombre) {
        return new DatosEscuela(nombre);
    }
}
