package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.servicios.CampoVacioException;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuelaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorEscuela {

    private ServicioEscuela servicioEscuela;

    @Autowired
    public ControladorEscuela(ServicioEscuela servicioEscuela){
        this.servicioEscuela = servicioEscuela;
    }

    @RequestMapping("/crear-escuela")
    public ModelAndView crearEscuela(){
        ModelMap modelo = new ModelMap();
        modelo.put("datosEscuela", new DatosEscuela());
        return new ModelAndView("crearEscuelas", modelo);
    }

    @RequestMapping(path = "/guardar-escuela", method = RequestMethod.POST)
    public ModelAndView guardar(@ModelAttribute("datosEscuela") DatosEscuela datosEscuela) {
        ModelMap modelo = new ModelMap();
        try{
            servicioEscuela.guardar(datosEscuela);
        }catch (CampoVacioException e){
            modelo.put("error", "Debe indicar un nombre de escuela");
            return new ModelAndView("crearEscuela", modelo);
        }
        return new ModelAndView("redirect:/listar-escuelas");
    }

    @RequestMapping(path = "/listar-escuelas")
    public ModelAndView listarEscuelas(){
        ModelMap modelo = new ModelMap();
        modelo.put("listaEscuelas", servicioEscuela.listarTodas());
        return new ModelAndView("listaEscuelas", modelo);
    }
}
