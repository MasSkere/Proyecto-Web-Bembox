package com.bembox.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bembox.entity.Cliente;
import com.bembox.entity.Usuario;
import com.bembox.repository.ClienteRepository;
import com.bembox.service.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> listarTodosCliente() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public boolean eliminarClienteById(Long id) {
		try {
	        clienteRepository.deleteById(id);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

	@Override
	public Cliente buscarClienteById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente buscarPorUsername(Usuario username) {
		// TODO Auto-generated method stub
		return clienteRepository.findByUsuario(username);
	}
	
	@Override
	public Cliente obtenerPorUsuarioId(Long id) {
	    return clienteRepository.findByUsuario_Id(id)
	        .orElseThrow(() -> new RuntimeException("Cliente no encontrado para el usuario"));
	}

	
	
}
