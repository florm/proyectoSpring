package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorPrueba {


    @RequestMapping(path = "/saludar", method = RequestMethod.GET)
    public ModelAndView saludar1(){
        ModelMap modelo = new ModelMap();
        modelo.put("mensaje1", "Hola como estas?");
        modelo.put("mensaje2", "Soy flor!");
        return new ModelAndView("saludo", modelo);
    }


}
