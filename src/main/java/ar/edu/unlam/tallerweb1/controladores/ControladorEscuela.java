package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Escuela;
import ar.edu.unlam.tallerweb1.servicios.ServicioEscuela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ControladorEscuela {

    private ServicioEscuela servicioEscuela;

    @Autowired
    public ControladorEscuela(ServicioEscuela servicioEscuela){
        this.servicioEscuela = servicioEscuela;
    }

    @RequestMapping(path = "/ir-a-buscar-escuela")
    public ModelAndView buscarEscuelas(){
        return new ModelAndView("escuelas");
    }

    @RequestMapping(path = "/buscar-escuela")
    public ModelAndView buscarPorNombre(@RequestParam("nombre") String nombre) {
        ModelMap modelo = new ModelMap();
        List<Escuela> listaEscuelas = servicioEscuela.buscarPor(nombre);
        if(listaEscuelas.size() == 0){
            modelo.put("sinResultado", "No se han encontrado Escuela con el nombre " + nombre);
        }
        modelo.put("listaEscuelas", listaEscuelas);
        return new ModelAndView("escuelas", modelo);
    }
}
