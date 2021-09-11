package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorRegistro {


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
        if(lasClavesSonDistintas(datosRegistro)){
            return registroFallido(modelo, "Las claves deben ser iguales");
        }
        if(laClaveTieneLongitudIncorrecta(datosRegistro)){
            return registroFallido(modelo, "La clave debe tener al menos 8 caracteres");
        }
        return registroExitoso();
    }

    private boolean laClaveTieneLongitudIncorrecta(DatosRegistro datosRegistro) {
        return datosRegistro.getClave().length() < 8;
    }

    private boolean lasClavesSonDistintas(DatosRegistro datosRegistro) {
        return !datosRegistro.getClave().equals(datosRegistro.getRepiteClave());
    }

    private ModelAndView registroExitoso() {
        return new ModelAndView("redirect:/login");
    }

    private ModelAndView registroFallido(ModelMap modelo, String mensaje) {
        modelo.put("error", mensaje);
        return new ModelAndView("registro-usuario", modelo);
    }

}
