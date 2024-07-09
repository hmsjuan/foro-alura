package hmsjuan.aluraforo.controller.domain.topico;

import hmsjuan.aluraforo.controller.domain.curso.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @CreationTimestamp
    private LocalDateTime fechacreacion;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DTORegistroTopico dtoRegistroTopico) {
        this.titulo = dtoRegistroTopico.titulo();
        this.mensaje = dtoRegistroTopico.mensaje();
        this.fechacreacion = LocalDateTime.now();
        this.curso = dtoRegistroTopico.curso();
    }

    public void setDtoActualizarTopico(DTOActualizarTopico dtoActualizarTopico) {
        if(dtoActualizarTopico.titulo() != null) {
            this.titulo = dtoActualizarTopico.titulo();
        }

        if(dtoActualizarTopico.mensaje() != null) {
            this.mensaje = dtoActualizarTopico.mensaje();
        }
    }

}
