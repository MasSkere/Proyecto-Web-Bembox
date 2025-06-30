package com.bembox.service;

import java.util.List;

import com.bembox.entity.Cliente;
import com.bembox.entity.Usuario;

public interface ClienteService {
	
	 Cliente guardarCliente(Cliente cliente);

	 List<Cliente> listarTodosCliente();

	 boolean eliminarClienteById(Long id);
	
	 Cliente buscarClienteById(Long id);
	 
	 Cliente buscarPorUsername(Usuario username);
	 
	 Cliente obtenerPorUsuarioId(Long id);

   
	 

}
