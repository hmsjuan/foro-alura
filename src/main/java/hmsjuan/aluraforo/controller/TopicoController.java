package hmsjuan.aluraforo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import hmsjuan.aluraforo.controller.domain.topico.DTOActualizarTopico;
import hmsjuan.aluraforo.controller.domain.topico.DTOTopico;
import hmsjuan.aluraforo.controller.domain.topico.DTOListadoTopico;
import hmsjuan.aluraforo.controller.domain.topico.DTORegistroTopico;
import hmsjuan.aluraforo.controller.domain.topico.Topico;
import hmsjuan.aluraforo.controller.domain.topico.TopicoRepository;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")

public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTema(@RequestBody @Valid DTORegistroTopico dtoRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var tema   = new Topico(dtoRegistroTopico);
        topicoRepository.save(tema);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(uri).body(new DTOTopico(tema));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListadoTopico>> datosListaTema(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable).map(DTOListadoTopico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTema(@RequestBody @Valid DTOActualizarTopico dtoActualizarTopico) {
        var tema = topicoRepository.getReferenceById(dtoActualizarTopico.id());
        tema.setDtoActualizarTopico(dtoActualizarTopico);
        return ResponseEntity.ok(new DTOTopico(tema));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTema(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTema(@PathVariable Long id) {
        var tema = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTOTopico(tema));
    }
}
