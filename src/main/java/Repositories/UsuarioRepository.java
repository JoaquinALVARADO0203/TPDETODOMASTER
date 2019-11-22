package Repositories;

import org.springframework.data.repository.CrudRepository;

import Models.UsuarioModel;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer>{

	UsuarioModel findByUsuario(String usuario);
	
}
