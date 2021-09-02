package ar.edu.unlam.tallerweb1.modelo;

public class CajaFuerte {


    private boolean abierta = true;
    private int codigo;

    public boolean estaAbierta() {
        return abierta;
    }

    public boolean estaCerrada() {
        return !abierta;
    }

    public void cerrar(int codigo) {
        abierta = false;
        this.codigo = codigo;
    }

    public void abrir(int codigo) {
        if(this.codigo == codigo)
            abierta = true;
    }
}
