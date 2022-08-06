package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoRespostaForm
import br.com.alura.forum.dto.NovaRespostaForm
import br.com.alura.forum.dto.RespostaView
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.service.RespostaService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos/{id}/respostas")
class RespostaController(private val service: RespostaService) {

    @GetMapping
    fun listar(@PathVariable id: Long): List<Resposta> {
        return service.buscarRespostasPorId(id)
    }

    @PostMapping()
    fun cadastrar(
        @PathVariable id: Long,
        @RequestBody @Valid dto: NovaRespostaForm,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<RespostaView> {
        val respostaView = service.cadastrar(id, dto)
        val uri = uriComponentsBuilder.path("/topicos/${id}/respostas").build().toUri()
        return ResponseEntity.created(uri).body(respostaView) // Retorna status 201 created, com dto no body e URI no Header Location.
    }

    @PutMapping()
    fun atualizar(@RequestBody @Valid dto: AtualizacaoRespostaForm): ResponseEntity<RespostaView> {
        val respostaView = service.atualizar(dto)
        return ResponseEntity.ok(respostaView) // Retorna status 200 ok com o dto no body.
    }

    @DeleteMapping("/{idResposta}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable idResposta: Long) {
        service.deletar(idResposta)
    }
}