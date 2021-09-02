package ar.edu.unlam.tallerweb1;

import ar.edu.unlam.tallerweb1.modelo.CajaFuerte;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class CajaFuerteTest {

    public final int CODIGO_CORRECTO = 1234;
    public final int CODIGO_INCORRECTO = 123456;

    @Test
    public void alCrearLaCajaFuerteDebeEstarAbierta(){
        //preparacion (given)
        givenNoExisteUnaCajaFuerte();
        //ejecucion (when)
        CajaFuerte cajaFuerte = whenCreoLaCajaFuerte();
        //comprobacion (then)
        thenLaCajaFuerteEstaAbierta(cajaFuerte);
    }

    @Test
    public void alCierrarLaCajaFuerteConUnCodigoDebeEstarCerrada(){
        CajaFuerte cajaFuerte =  givenExisteUnaCajaFuerte();

        whenCierroLaCajaFuerteConCodigo(cajaFuerte, CODIGO_CORRECTO);

        thenLaCajaFuerteEstaCerrada(cajaFuerte);
    }

    @Test
    public void alIngresarUnCodigoCorrectoLaCajaFuerteSeAbre(){
        CajaFuerte cajaFuerte =  givenExisteUnaCajaFuerte();
        givenCierroLaCajaFuerte(cajaFuerte, CODIGO_CORRECTO);

        whenAbroLaCajaFuerteConCodigoCorrecto(cajaFuerte, CODIGO_CORRECTO);

        thenLaCajaFuerteEstaAbierta(cajaFuerte);
    }

    @Test
    public void alIngresarUnCodigoIncorrectoLaCajaFuerteNoSeAbre(){
        CajaFuerte cajaFuerte =  givenExisteUnaCajaFuerte();
        givenCierroLaCajaFuerte(cajaFuerte, CODIGO_CORRECTO);

        whenAbroLaCajaFuerteConCodigoIncorrecto(cajaFuerte, CODIGO_INCORRECTO);

        thenLaCajaFuerteEstaCerrada(cajaFuerte);
    }

    private void whenAbroLaCajaFuerteConCodigoIncorrecto(CajaFuerte cajaFuerte, int codigo) {
        cajaFuerte.abrir(codigo);
    }

    private void givenCierroLaCajaFuerte(CajaFuerte cajaFuerte, int codigo) {
        cajaFuerte.cerrar(codigo);
    }

    private void whenAbroLaCajaFuerteConCodigoCorrecto(CajaFuerte cajaFuerte, int codigo) {
        cajaFuerte.abrir(codigo);
    }

    private void whenCierroLaCajaFuerteConCodigo(CajaFuerte cajaFuerte, int codigo) {
        cajaFuerte.cerrar(codigo);
    }


    private CajaFuerte givenExisteUnaCajaFuerte() {
        return crearCajaFuerte();
    }

    private void thenLaCajaFuerteEstaCerrada(CajaFuerte cajaFuerte) {
        assertThat(cajaFuerte.estaCerrada()).isTrue();
    }

    private void givenNoExisteUnaCajaFuerte() {
    }

    private CajaFuerte whenCreoLaCajaFuerte() {
        return crearCajaFuerte();
    }

    private void thenLaCajaFuerteEstaAbierta(CajaFuerte cajaFuerte) {
        assertThat(cajaFuerte.estaAbierta()).isTrue();
    }

    private CajaFuerte crearCajaFuerte(){
        return new CajaFuerte();
    }
}
