package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.CampoVacioException;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuelaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ControladorEscuela {

    private ServicioEscuela servicioEscuela;

    @Autowired
    public ControladorEscuela(ServicioEscuela servicioEscuela){
        this.servicioEscuela = servicioEscuela;
    }

    public ModelAndView guardar(DatosEscuela datosEscuela) {
        ModelMap modelo = new ModelMap();
        try{
            servicioEscuela.guardar(datosEscuela);
        }catch (CampoVacioException e){
            modelo.put("error", "Debe indicar un nombre de escuela");
            return new ModelAndView("crearEscuela", modelo);
        }
        modelo.put("listaEscuelas", new ArrayList());
        return new ModelAndView("listaEscuelas", modelo);
    }
}
