package br.com.alura.forum.controller

import br.com.alura.forum.dto.NovoTopicoDto
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// @Controller: Usamos quando estamos trabalhando numa aplicação server-side, onde as páginas são geradas através de JSP, time leaf, etc (renderização).
@RestController // É uma classe controller, só que é RestController, irei apenas receber dados e devolver dados.
@RequestMapping("/topicos") // Pra conseguirmos identificar o endereço, temos que ter a URI /topicos.
// Classe que vai receber as requisições dos clientes e fazer as devidas manipulações. O Padrão do Spring é receber o padrão Json (automaticamente usando a biblioteca Jackson)
class TopicoController(val service: TopicoService) {

    @GetMapping // Verbo GET com URI /topicos. Responsável por devolver dados.
    fun listar(): List<Topico> {
        return service.listar()
    }

    @GetMapping("/{id}") // Parâmetro dinâmico, faz parte do path da URI
    fun buscarPorId(@PathVariable id: Long): Topico {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar(@RequestBody dto: NovoTopicoDto) { // RequestBody serve pra informar ao Spring que as informações desse POST estarão no Corpo da Requisição.
        // Além disso, lá no postman, dentro de Headers, precisamos informar o Content-Type: application/json para informar ao servidor o formato do conteudo da requisição que estamos enviando.
        // Em Body, selecionamos a opção raw.
        service.cadastrar(dto)
    }

    @GetMapping("/teste/{nome}") // Simulação de erro de parâmetro não encontrado MissingPathVariableException, pois na URI está nome e no parametro está name:
    fun buscarPorNome(@PathVariable name: String): String {
        return name
    }

}