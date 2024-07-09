package hmsjuan.aluraforo.controller.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import hmsjuan.aluraforo.controller.domain.usuario.Usuario;
import hmsjuan.aluraforo.controller.domain.usuario.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("No se encontro el usuario " + username);
        }
        return usuario;
    }

}
