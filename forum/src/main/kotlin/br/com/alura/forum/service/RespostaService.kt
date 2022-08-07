package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoRespostaForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.RespostaFormMapper
import br.com.alura.forum.mapper.RespostaViewMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class RespostaService(
    private var respostas: List<Resposta>,
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper,
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
        val topico2 = Topico(
            id = 2,
            titulo = "Duvida Java",
            mensagem = "Como fazer um Hello World no Java?",
            curso = curso,
            autor = autor
        )

        val resposta = Resposta(
            id = 1,
            mensagem = "Resposta 1 Kotlin",
            autor = autor,
            topico = topico,
            solucao = false
        )

        val resposta2 = Resposta(
            id = 2,
            mensagem = "Resposta 1 Java",
            autor = autor,
            topico = topico2,
            solucao = false
        )

        val resposta3 = Resposta(
            id = 3,
            mensagem = "Resposta 2 Java",
            autor = autor,
            topico = topico2,
            solucao = false
        )

        respostas = Arrays.asList(resposta, resposta2, resposta3)
    }

    fun buscarRespostasPorId(idTopico: Long): List<Resposta> {
        return respostas.stream().filter {
                r -> r.topico?.id == idTopico // Busca a lista de resposta
        }.collect(Collectors.toList())
    }

    fun buscarRespostaPorId(idResposta: Long): Resposta {
        val resposta = respostas.stream().filter { r -> r.id == idResposta }.findFirst().orElseThrow{ NotFoundException("Resposta não encontrada!") }
        return resposta
    }

    fun cadastrar(idTopico: Long, dto: NovaRespostaForm): RespostaView {
        val novaResposta = respostaFormMapper.map(dto)
        novaResposta.id = respostas.size.toLong() + 1
        novaResposta.topico = topicoService.buscarPorIdOld(idTopico)
        respostas = respostas.plus(novaResposta)
        return respostaViewMapper.map(novaResposta)
    }

    fun atualizar(form: AtualizacaoRespostaForm): RespostaView {
        val resposta = buscarRespostaPorId(form.id)
        val novaResposta = Resposta(
            id = resposta.id,
            mensagem = form.mensagem,
            dataCriacao = resposta.dataCriacao,
            autor = resposta.autor,
            topico = resposta.topico,
            solucao = resposta.solucao
        )
        respostas = respostas
            .minus(resposta)        // Remove registro antigo.
            .plus(novaResposta)     // Adiciona regiustro novo.
        return respostaViewMapper.map(novaResposta)
    }

    fun deletar(id: Long) {
        val resposta = buscarRespostaPorId(id)
        respostas = respostas.minus(resposta)
    }

}
