package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(path = "/ir-a-suma", method = RequestMethod.GET)
    public ModelAndView irAFormulario(){
        ModelMap modelo = new ModelMap();
        return new ModelAndView("form-ejemplo");
    }

    @RequestMapping(path = "/sumar", method = RequestMethod.GET)
    public ModelAndView sumar(@RequestParam("op1") Integer op1, @RequestParam("op2") Integer op2){
        ModelMap modelo = new ModelMap();
        Integer resultado = op1 + op2;
        modelo.put("resultado", resultado);
        return new ModelAndView("form-ejemplo", modelo);
    }

}
