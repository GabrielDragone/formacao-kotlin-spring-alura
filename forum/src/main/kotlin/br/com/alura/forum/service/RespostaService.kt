package br.com.alura.forum.service

import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors

@Service
class RespostaService(
    private var respostas: List<Resposta>,
    private val respostaFormMapper: RespostaFormMapper,
    private val topicoService: TopicoService
    ) {

    init {
        val curso = Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programacao"
        )
        val autor = Usuario(
            id = 1,
            nome = "Ana da Silva",
            email = "ana@email.com"
        )
        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis no Kotlin",
            curso = curso,
            autor = autor
        )

        val resposta = Resposta(
            id = 1,
            mensagem = "Resposta 1",
            autor = autor,
            topico = topico,
            solucao = false
        )

        val resposta2 = Resposta(
            id = 2,
            mensagem = "Resposta 2",
            autor = autor,
            topico = topico,
            solucao = false
        )

        respostas = Arrays.asList(resposta, resposta2)
    }

    fun buscarRespostasPorId(idTopico: Long): List<Resposta> {
        return respostas.stream().filter({
                r -> r.topico?.id == idTopico // Busca a lista de resposta
        }).collect(Collectors.toList())
    }

    fun cadastrar(idTopico: Long, dto: NovaRespostaForm) {
        // Transformar o DTO NovaRespostaForm numa Resposta:
        val novaResposta = respostaFormMapper.map(dto)
        novaResposta.id = respostas.size.toLong() + 1
        novaResposta.topico = topicoService.buscarPorIdOld(idTopico)
        respostas = respostas.plus(novaResposta)
    }

}
