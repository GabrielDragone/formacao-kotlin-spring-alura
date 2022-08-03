package br.com.alura.forum.controller

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

// @Controller: Usamos quando estamos trabalhando numa aplicação server-side, onde as páginas são geradas através de JSP, time leaf, etc (renderização).
@RestController // É uma classe controller, só que é RestController, irei apenas receber dados e devolver dados.
@RequestMapping("/topicos") // Pra conseguirmos identificar o endereço, temos que ter a URI /topicos.
// Classe que vai receber as requisições dos clientes e fazer as devidas manipulações. O Padrão do Spring é receber o padrão Json (automaticamente usando a biblioteca Jackson)
class TopicoController {

    @GetMapping // Verbo GET com URI /topicos
    fun listar(): List<Topico> {

        val topico = Topico(
            id = 1,
            titulo = "Duvida Kotlin",
            mensagem = "Variaveis do Kotlin",
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

        return Arrays.asList(topico, topico, topico) // Mandamos de volta 3 vezes o mesmo tópico.
    }
}