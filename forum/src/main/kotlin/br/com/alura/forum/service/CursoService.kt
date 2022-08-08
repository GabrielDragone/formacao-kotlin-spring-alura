package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Curso
import br.com.alura.forum.repository.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(
    //private var cursos: List<Curso> // Retirado pois agora passamos a utilizar o banco H2 através do CursoRepository
    private val cursoRepository: CursoRepository
    ) {

    // Retirado pois agora passamos a utilizar o banco H2 através do CursoRepository:
    /*init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )

        cursos = Arrays.asList(curso)
    }*/

    fun buscarPorId(id: Long): Curso {
        // Alterado pois agora passamos a utilizar o banco H2 através do CursoRepository:
        /*return cursos.stream().filter(
            { c -> c.id == id }
        ).findFirst().orElseThrow { NotFoundException("Curso não encontrado!") }*/
        return cursoRepository.getOne(id)
    }

}
