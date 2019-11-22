package Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;


@RestController
@RequestMapping("adentro")
public class PrimerController {
	
	@GetMapping("/")
	public Integer comprobar(HttpSession session) {
		
		if(session.getAttribute("usuario_Id") == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No esta conectado");
	}
	return (Integer) session.getAttribute("usuario_id");
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}


