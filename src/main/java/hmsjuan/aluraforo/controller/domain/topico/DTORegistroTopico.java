package hmsjuan.aluraforo.controller.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import hmsjuan.aluraforo.controller.domain.curso.Curso;

public record DTORegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull @Valid
        Curso curso
) {
}
