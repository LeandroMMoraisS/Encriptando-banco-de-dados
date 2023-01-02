package AprendendoCriptogtafia.JWT.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import AprendendoCriptogtafia.JWT.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
   
	
	public Optional<Usuario> findByLogin(String login);

}
