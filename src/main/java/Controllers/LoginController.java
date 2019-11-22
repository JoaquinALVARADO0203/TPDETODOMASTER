package Controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.*;
import javax.xml.bind.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;
import Models.UsuarioModel;
import Repositories.UsuarioRepository;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/")	
	public String login(@RequestBody UsuarioModel login, HttpSession session)
			throws NoSuchAlgorithmException {
	
	UsuarioModel usuario = null;
	usuario = this.usuarioRepository.findByUsuario(login.getUsuario());
	if(usuario == null) {
	   throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario no encontrado");
	}
	
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(login.getContrasenia().getBytes());
	byte[] digest = md.digest();
	String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
	
	if(!myHash.equals(usuario.getContrasenia())) {	
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Clave incorrecta: "+login.getContrasenia()+" la clave guardada es: "+usuario.getContrasenia());
	}
	session.setAttribute("usuario_id",usuario.getId());
	
	return "ok";
	
	}
	
	
	
	
}
