package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class MiPrimerControlador {

    @RequestMapping(path= "/miprimerurl")
    public ModelAndView primeraUrl(){
        return new ModelAndView("vista1");

    }

    @RequestMapping(path= "/segundavista")
    public ModelAndView mostrarVista2(@RequestParam(value="n") String nombre,
                                      @RequestParam(value="a") String apellido){
        ModelMap modelo = new ModelMap();
        modelo.put("nombre", nombre);
        modelo.put("apellido", apellido);
        return new ModelAndView("vista2", modelo);

    }

    @RequestMapping(path= "/segundavista/categoria/{nombreCategoria}")
    public ModelAndView mostrarVista2(@PathVariable(value="nombreCategoria") String nombre){
        ModelMap modelo = new ModelMap();
        modelo.put("nombre", nombre);
        return new ModelAndView("vista2", modelo);

    }

    /*
    * sitio/segundavista?n=flor&a=martin
    *
    * sitio/categoria/{nombreCategoria}

    *
    * */


}
