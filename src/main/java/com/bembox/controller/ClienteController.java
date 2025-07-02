package com.bembox.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import com.bembox.entity.Cliente;
import com.bembox.entity.Usuario;
import com.bembox.service.ClienteService;
import com.bembox.service.UsuarioService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/home")
    public String homeCliente(Model model, Authentication authentication) {
        String username = authentication.getName(); // nombre_usuario

        // Buscamos al usuario por su username
        Optional<Usuario> usuarioOpt = usuarioService.findByNombreUsuario(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Buscamos al cliente asociado al usuario
            Cliente cliente = clienteService.buscarPorUsername(usuario);

            // Enviamos su nombre a la vista
            model.addAttribute("nombreCliente", cliente.getNombres());
        }

        return "cliente/home";
    }
    
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosCliente());
        return "cliente/list";
    }

    
	@GetMapping("/cliente/new")
	public String createCliente(Model model){	        
	  	Cliente cliente = new Cliente();	       
	    model.addAttribute("cliente", cliente);	       
	    return "cliente/create";	     
	}	
	
	@PostMapping("/cliente")
	public String saveCliente(Cliente cliente) {
		clienteService.guardarCliente(cliente);
	    return "redirect:/cliente";
	}
	
	@GetMapping("/cliente/edit/{id}")
	public String editCliente(@PathVariable Long id, Model model) {
	    Cliente st = clienteService.buscarClienteById(id);	       
	    model.addAttribute("cliente", st);	        
	    return "cliente/edit";
	}	
	
	@PostMapping("/cliente/{id}")
	public String updateCliente(@PathVariable Long id, Cliente cliente, Model model) {
        Cliente existentCliente = clienteService.buscarClienteById(id);
        existentCliente.setId(id);
       
        clienteService.guardarCliente(existentCliente);	        
        return "redirect:/cliente";
    }
	    
	@GetMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable Long id) {
		clienteService.eliminarClienteById(id);
        return "redirect:/cliente";
    }	
    
}