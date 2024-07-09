package hmsjuan.aluraforo.controller.domain.topico;

import java.time.LocalDateTime;
import hmsjuan.aluraforo.controller.domain.curso.Curso;

public record DTOTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Curso curso
) {

    public DTOTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechacreacion(), topico.getCurso());
    }
}
