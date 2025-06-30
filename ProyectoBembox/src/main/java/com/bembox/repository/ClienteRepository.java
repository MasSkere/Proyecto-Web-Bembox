package com.bembox.repository;

import java.util.Optional;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bembox.entity.Cliente;
import com.bembox.entity.Usuario;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByUsuario(Usuario usuario);
	
	Optional<Cliente> findByUsuario_Id(Long id);
	

	


}
