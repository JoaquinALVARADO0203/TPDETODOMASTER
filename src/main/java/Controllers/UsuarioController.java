package Controllers;

import java.security.*;

import javax.xml.bind.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import Models.*;
import Repositories.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/")
	public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) throws NoSuchAlgorithmException{
	
		System.out.println("llego hasta aca");
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(usuario.getContrasenia().getBytes());
		byte[] digest = md.digest();
		System.out.println("llego hasta aca");
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		if(!myHash.equals(usuario.getContrasenia())) {	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clave incorrecta: "+usuario.getContrasenia()+" la clave guardada es: "+usuario.getContrasenia());
		}
		System.out.println("llego hasta aca");
	usuario.setContrasenia(myHash);
	usuarioRepository.save(usuario);
	return usuario;
	
	
	
	
	}
	
}
