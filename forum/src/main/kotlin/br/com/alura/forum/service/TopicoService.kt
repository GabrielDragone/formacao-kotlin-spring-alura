package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service // Spring, essa é uma classe que representa Serviço. Dessa forma, conseguimos Injetar essa classe em qualquer outra classe
// que também é gerenciada pelo Spring. Dai no nosso Controller, colocamos essa Service dentro do Construtor da mesma para que
// o Spring saiba que quando ele for instanciar a Controller a Service depende dela.
class TopicoService(
    //private var topicos: List<Topico>, // Forma antiga para usar junto com o init, porém, o add não vai funfar pq toda vez que a classe é chamada resetaria.
    private var topicos: List<Topico> = ArrayList(),
    //private val  cursoService: CursoService, // Retiramos daqui e inserimos no TopicoFormMapper
    //private val usuarioService: UsuarioService,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
    ) {

    // Bloco de inicialização da classe:
    /*init {
        val topico1 = Topico(
            id = 1,
            titulo = "Duvida Kotlin 1",
            mensagem = "Variaveis do Kotlin 1",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Alves",
                email = "gabriel.alves@email.com"
            )
        )

        val topico2 = Topico(
            id = 2,
            titulo = "Duvida Kotlin 2",
            mensagem = "Variaveis do Kotlin 2",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Alves",
                email = "gabriel.alves@email.com"
            )
        )

        val topico3 = Topico(
            id = 3,
            titulo = "Duvida Kotlin 3",
            mensagem = "Variaveis do Kotlin 3",
            curso = Curso(
                id = 1,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1,
                nome = "Gabriel Alves",
                email = "gabriel.alves@email.com"
            )
        )

        topicos = Arrays.asList(topico1, topico2, topico3) // Mandamos de volta 3 vezes o mesmo tópico.
    }*/

    fun listarOld(): List<Topico> {
        return topicos
    }

    fun listar(): List<TopicoView> {
        return topicos.stream().map {
                t ->  topicoViewMapper.map(t) // Toda a implementação que está dentro do return do map estava aqui antigamente
        }.collect(Collectors.toList()) // No final convertemos para uma Lista.
    }

    fun buscarPorIdOld(id: Long): Topico {
        return topicos.stream().filter( { // API de stream
                t -> t.id == id           // Cada t é igual a um Topico, pesquisando por ID
        }).findFirst().get()              // Pega o primeiro e retorna
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return topicoViewMapper.map(topico) // Toda a implementação que está dentro do return do map estava aqui antigamente
    }

    fun cadastrar(form: NovoTopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico) // O list.plus(objeto) é equivalente ao list.add(objeto) do Java. Ele vai adicionar um elemento em uma lista. É necessário fazer o topicos = pois a lista é imutavel.
    }

    fun atualizar(form: AtualizacaoTopicoForm) {
        val topico = buscarPorIdOld(form.id)

        topicos = topicos   // Temos que fazer dessa forma pq a lista é imutável.
            .minus(topico)  // lista.minus(objeto): Remove o tópico.
            .plus(          // Adiciona o novo tópico enviado novamente, utilizando dados do form e do topico antigo.
                Topico(
                    id = form.id,
                    titulo = form.titulo,
                    mensagem = form.mensagem,
                    dataCriacao = topico.dataCriacao,
                    curso = topico.curso,
                    autor = topico.autor,
                    status = topico.status,
                    resposta = topico.resposta
                )
            )

    }

    fun deletar(id: Long) {
        val topico = buscarPorIdOld(id)
        topicos = topicos.minus(topico)
    }

}