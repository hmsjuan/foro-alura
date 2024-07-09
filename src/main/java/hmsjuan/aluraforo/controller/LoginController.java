package hmsjuan.aluraforo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hmsjuan.aluraforo.controller.domain.usuario.DTOAuthentication;
import hmsjuan.aluraforo.controller.domain.usuario.DTOJwtToken;
import hmsjuan.aluraforo.controller.infra.security.TokenService;
import hmsjuan.aluraforo.controller.domain.usuario.Usuario;


@RestController
@RequestMapping("/login")

public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity realizarLogin(@RequestBody @Valid DTOAuthentication dtoAuthentication) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAuthentication.login(), dtoAuthentication.clave());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DTOJwtToken(tokenJWT));
    }
}
