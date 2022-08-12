package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service // Spring, essa é uma classe que representa Serviço. Dessa forma, conseguimos Injetar essa classe em qualquer outra classe
// que também é gerenciada pelo Spring. Dai no nosso Controller, colocamos essa Service dentro do Construtor da mesma para que
// o Spring saiba que quando ele for instanciar a Controller a Service depende dela.
class TopicoService(
    //private var topicos: List<Topico>, // Forma antiga para usar junto com o init, porém, o add não vai funfar pq toda vez que a classe é chamada resetaria.
    //private var topicos: List<Topico> = ArrayList(), // Retirado pois agora passamos a utilizar o banco H2 através do TopicoRepository
    private val topicoRepository: TopicoRepository,
    //private val  cursoService: CursoService, // Retiramos daqui e inserimos no TopicoFormMapper
    //private val usuarioService: UsuarioService,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Tópico não encontrado!"
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
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        //return topicos
        return topicoRepository.findAll()
    }

    // Antigo antes de inserir o parâmetro não obrigatório:
//    fun listar(): List<TopicoView> {
//        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
//        //return topicos.stream().map {
//        return topicoRepository.findAll().stream().map {
//                t ->  topicoViewMapper.map(t) // Toda a implementação que está dentro do return do map estava aqui antigamente
//        }.collect(Collectors.toList()) // No final convertemos para uma Lista.
//    }

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
//    ): List<TopicoView> {
    ): Page<TopicoView> {
        val topicos = if (nomeCurso == null) { // Se o nomeCurso não estiver preenchido, pega todos os cursos e retorna pra topicos.
            topicoRepository.findAll(paginacao) // O findAll tem a implementação com e sem o Pageable e o próprio Spring sabe que deverá limitar a consulta limitando o retorno.
        } else {
            //topicoRepository.findByCursoNome(nomeCurso) // Se estiver, busca pelo nomeCurso passado e retorna para topicos.
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }

        // Implementação de List:
        /*return topicos.stream().map {
                topico -> topicoViewMapper.map(topico)
        }.collect(Collectors.toList())*/

        // Implementação com Page:
        return topicos.map {
                topico -> topicoViewMapper.map(topico)
        }

    }

    fun buscarPorIdOld(id: Long): Topico {
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        //return topicos.stream().filter( { // API de stream
        return topicoRepository.findAll().stream().filter( { // API de stream
                t -> t.id == id           // Cada t é igual a um Topico, pesquisando por ID
//        }).findFirst().get()              // Pega o primeiro e retorna
        }).findFirst().orElseThrow { NotFoundException(message = notFoundMessage) }            // Pega o primeiro e retorna
    }

    fun buscarPorId(id: Long): TopicoView {
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        /*val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()*/
        val topico = topicoRepository.findById(id).orElseThrow { NotFoundException(message = notFoundMessage) } // Repositório, busque por ID, se não encontrar, retorna o Optional com Exception.

        return topicoViewMapper.map(topico) // Toda a implementação que está dentro do return do map estava aqui antigamente
    }

    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        //topico.id = topicos.size.toLong() + 1
        //topicos = topicos.plus(topico) // O list.plus(objeto) é equivalente ao list.add(objeto) do Java. Ele vai adicionar um elemento em uma lista. É necessário fazer o topicos = pois a lista é imutavel.
        topicoRepository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        //val topico = buscarPorIdOld(form.id)
        val topico = topicoRepository.findById(form.id).orElseThrow { NotFoundException(message = notFoundMessage) }

        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        /*val topicoAtualizado = Topico(
            id = form.id,
            titulo = form.titulo,
            mensagem = form.mensagem,
            dataCriacao = topico.dataCriacao,
            curso = topico.curso,
            autor = topico.autor,
            status = topico.status,
            resposta = topico.resposta
        )*/

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem

        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        /*topicos = topicos               // Temos que fazer dessa forma pq a lista é imutável.
            .minus(topico)              // lista.minus(objeto): Remove o tópico.
            .plus(topicoAtualizado)     // Adiciona o novo tópico enviado novamente, utilizando dados do form e do topico antigo.
        */
        topicoRepository.save(topico)

        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        //return topicoViewMapper.map(topico)
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        // Alterado pois agora passamos a utilizar o banco H2 através do TopicoRepository:
        /*val topico = buscarPorIdOld(id)
        topicos = topicos.minus(topico)*/
        topicoRepository.deleteById(id)
    }

}