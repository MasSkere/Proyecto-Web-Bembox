package com.bembox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bembox.service.ProductoService;



@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String mostrarHome(Model model) {
    	 model.addAttribute("productosRecomendados", productoService.listarProductosRecomendados());
    	    return "home";
    }
    

    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "nosotros";
    }
}
