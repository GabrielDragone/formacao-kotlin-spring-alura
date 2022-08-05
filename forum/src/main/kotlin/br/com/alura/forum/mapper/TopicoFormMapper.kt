package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val  cursoService: CursoService, // Constructor Injection
    private val usuarioService: UsuarioService,
): Mapper<NovoTopicoForm, Topico> { // TopicoFormMapper implementa interface Mapper.

    override fun map(form: NovoTopicoForm): Topico {
        return Topico(
            //id = topicos.size.toLong() + 1, // Tiramos pq é opcional
            titulo = form.titulo,
            mensagem = form.mensagem,
            curso = cursoService.buscarPorId(form.idCurso),
            autor = usuarioService.buscarPorId(form.idAutor)
        )
    }

}
