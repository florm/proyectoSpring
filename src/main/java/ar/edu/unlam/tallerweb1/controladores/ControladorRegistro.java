package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.TablaUsuario;
import ar.edu.unlam.tallerweb1.servicios.ClaveLongitudIncorrectaException;
import ar.edu.unlam.tallerweb1.servicios.ClavesDistintasException;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.UsuarioYaExisteException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {


    private TablaUsuario tablaUsuario = TablaUsuario.getInstance();
    private ServicioUsuario servicioUsuario = new ServicioUsuario();

    //voy al registro del usuario
    @RequestMapping(path = "/registrar-usuario", method = RequestMethod.GET)
    public ModelAndView mostrarFormularioDeRegistro(){
        ModelMap modelo = new ModelMap();
        modelo.put("datosRegistro", new DatosRegistro());
        return new ModelAndView("registro-usuario",modelo);
    }

    //registro usuario
    @RequestMapping(path = "/registrarme", method = RequestMethod.POST)
    public ModelAndView registrar(@ModelAttribute("datosRegistro") DatosRegistro datosRegistro) {
        ModelMap modelo = new ModelMap();
        try{
            servicioUsuario.registrar(datosRegistro);
        }catch (ClavesDistintasException e){
            return registroFallido(modelo, "Las claves deben ser iguales");
        }catch(ClaveLongitudIncorrectaException e){
            return registroFallido(modelo, "La clave debe tener al menos 8 caracteres");
        }catch (UsuarioYaExisteException e){
            return registroFallido(modelo, "El usuario ya se encuentra registrado");
        }
        return registroExitoso();
    }

    private ModelAndView registroExitoso() {
        return new ModelAndView("redirect:/login");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("registro-usuario", modelo);
    }

}
