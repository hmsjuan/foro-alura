package hmsjuan.aluraforo.controller.domain.topico;

import hmsjuan.aluraforo.controller.domain.curso.Curso;

public record DTOListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        Curso curso
) {

    public DTOListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }

}
