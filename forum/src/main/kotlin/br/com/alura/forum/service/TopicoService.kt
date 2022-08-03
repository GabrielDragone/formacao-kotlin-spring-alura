package br.com.alura.forum.service

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Predicate

@Service // Spring, essa é uma classe que representa Serviço. Dessa forma, conseguimos Injetar essa classe em qualquer outra classe
// que também é gerenciada pelo Spring. Dai no nosso Controller, colocamos essa Service dentro do Construtor da mesma para que
// o Spring saiba que quando ele for instanciar a Controller a Service depende dela.
class TopicoService(private var topicos: List<Topico>) {

    // Bloco de inicialização da classe:
    init {
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
    }

    fun listar(): List<Topico> {
        return topicos
    }

    fun buscarPorId(id: Long): Topico {
        return topicos.stream().filter( { // API de stream
                t -> t.id == id           // Cada t é igual a um Topico, pesquisando por ID
        }).findFirst().get()              // Pega o primeiro e retorna
    }

}