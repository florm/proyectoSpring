package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.controladores.DatosRegistro;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

public class TablaUsuario {

    private static final TablaUsuario instance = new TablaUsuario();
    private Map<String, Usuario> tablaUsuario = new HashMap<>();

    private TablaUsuario(){}

    public static TablaUsuario getInstance(){
        return instance;
    }

    public boolean existeUsuarioCon(String email){
        return this.tablaUsuario.containsKey(email);
    }

    public void agregar(Usuario usuario){
        this.tablaUsuario.put(usuario.getEmail(), usuario);
    }
    public void reset(){
        this.tablaUsuario.clear();
    }
}
