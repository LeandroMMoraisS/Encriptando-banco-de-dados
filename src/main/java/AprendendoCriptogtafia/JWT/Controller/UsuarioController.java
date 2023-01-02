package AprendendoCriptogtafia.JWT.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import AprendendoCriptogtafia.JWT.Model.Usuario;
import AprendendoCriptogtafia.JWT.Repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")

public class UsuarioController 
{
	@Autowired
	UsuarioRepository usuariorepository;
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/todos")
	public ResponseEntity<?> RetornaTodos()
	{
		
		return 	ResponseEntity.ok(usuariorepository.findAll());
	}	
	
	
	@PostMapping
	public ResponseEntity<?> AdicionaUsuario(@RequestBody Usuario usuario)
	{
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return ResponseEntity.ok(usuariorepository.save(usuario));
		
	}
	@GetMapping("/Consultar")
	public ResponseEntity<Boolean>Consulta(String Login, String Senha)
	{
		Optional<Usuario> optUsuario = usuariorepository.findByLogin(Login);
		if (optUsuario.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		Usuario usuario = optUsuario.get();
        boolean valid = encoder.matches(Senha, usuario.getSenha());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);

	}
	
	
}
