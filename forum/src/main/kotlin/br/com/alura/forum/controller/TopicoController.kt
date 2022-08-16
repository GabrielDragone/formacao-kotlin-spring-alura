package br.com.alura.forum.controller

import br.com.alura.forum.dto.AtualizacaoTopicoForm
import br.com.alura.forum.dto.NovoTopicoForm
import br.com.alura.forum.dto.TopicoPorCategoriaDto
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

// @Controller: Usamos quando estamos trabalhando numa aplicação server-side, onde as páginas são geradas através de JSP, time leaf, etc (renderização).
@RestController // É uma classe controller, só que é RestController, irei apenas receber dados e devolver dados.
@RequestMapping("/topicos") // Pra conseguirmos identificar o endereço, temos que ter a URI /topicos.
// Classe que vai receber as requisições dos clientes e fazer as devidas manipulações. O Padrão do Spring é receber o padrão Json (automaticamente usando a biblioteca Jackson)
class TopicoController(val service: TopicoService) {

    @GetMapping // Verbo GET com URI /topicos. Responsável por devolver dados.
//    fun listar(): List<TopicoView> {
    @Cacheable("topicos") // A primeira vez que for chamado, vai buscar normalmente. A partir da segunda, vai usar o Cache. "topicos" é a identificação.
    fun listar(
        @RequestParam(required = false) nomeCurso: String?, // Busca por nomeCurso. Parâmetro não obrigatório. Se não vier, passará nulo.
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable // O Spring sabe que esse parâmetro ele não vai receber do Cliente. Ele mesmo deve
        // instanciar e passar pra ca. O cliente manda os dados para filtrar quantos registros quer, por exemplo. Parametro size = 5, ou page = 1.
        // @PageableDefault, são os dados que, se não forem enviados na requisição, irão atender o Default.
        // Inserindo o sort, informamos por qual atributo vai ordenar e o direction pra falar se é ASC ou DESC.
//    ): List<TopicoView> {
    ): Page<TopicoView> {
        return service.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}") // Parâmetro dinâmico, faz parte do path da URI
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.buscarPorId(id)
    }

    @PostMapping // Utilizamos POST quando queremos cadastrar algo.
    @Transactional // Contexto de transação, uma transação pra inicializar, rodar os comandos SQL e commitar as alterações.
    @CacheEvict(value = ["topicos"], allEntries = true) // Toda vez que chamar esse endpoint, serão limpados os Cache dos registros em array(nesse caso topicos)). O allEntries serve pra informar que queremos revomer todos os registros.
    fun cadastrar(
        @RequestBody @Valid form: NovoTopicoForm,
        uriComponentsBuilder: UriComponentsBuilder // Classe do Spring que auxilia na criação da URI. Já vai puxar sozinho o endereço do servidor.
    ): ResponseEntity<TopicoView> {
        // @RequestBody serve pra informar ao Spring que as informações desse POST estarão no Corpo da Requisição.
        // Além disso, lá no postman, dentro de Headers, precisamos informar o Content-Type: application/json para informar ao servidor o formato do conteudo da requisição que estamos enviando.
        // Em Body, selecionamos a opção raw.
        // @Valid: Informamos ao Spring que queremos que ele valide o NovoTopicoForm com as bean validations que colocamos em seus campos.
        // Se houver alguma violação, o Spring vai retornar o erro 400 de bad request.
        val topicoView = service.cadastrar(form)
        val uri = uriComponentsBuilder.path("/topicos/${topicoView.id}").build().toUri() // Converte do uriBuilder para o objeto URI que é esperado no return.
        return ResponseEntity.created(uri).body(topicoView) // Retorna o status 201 (created), junto com a uri para o Header Location e o dado criado para visualização no Body.
    }

    @PutMapping // Se vier uma requisição do tipo PUT, significa que queremos atualizar um determinado registro.
    @Transactional // Contexto de transação, uma transação pra inicializar, rodar os comandos SQL e commitar as alterações.
    @CacheEvict(value = ["topicos"], allEntries = true) // Toda vez que chamar esse endpoint, serão limpados os Cache dos registros em array(nesse caso topicos)). O allEntries serve pra informar que queremos revomer todos os registros.
    fun atualizar(@RequestBody @Valid form: AtualizacaoTopicoForm): ResponseEntity<TopicoView> {
        val topicoView = service.atualizar(form)
        return ResponseEntity.ok(topicoView) // Retorna status 200 (ok), junto com o dado atualizado para visualização no Body.
    }

    @DeleteMapping("/{id}") // Verbo Delete, quando queremos remover determinado registro.
    @ResponseStatus(HttpStatus.NO_CONTENT) // Se a requisição for processada com sucesso, não teremos nenhum dado de retorno, então retornaremos o Status 204 (No Content)
    @Transactional // Contexto de transação, uma transação pra inicializar, rodar os comandos SQL e commitar as alterações.
    @CacheEvict(value = ["topicos"], allEntries = true) // Toda vez que chamar esse endpoint, serão limpados os Cache dos registros em array(nesse caso topicos)). O allEntries serve pra informar que queremos revomer todos os registros.
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

    @GetMapping("/teste/{nome}") // Simulação de erro de parâmetro não encontrado MissingPathVariableException, pois na URI está nome e no parametro está name:
    fun buscarPorNome(@PathVariable name: String): String {
        return name
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return service.relatorio()
    }

    @GetMapping("/pendentes")
    fun pendentes(): List<Topico> {
        return service.pendentes()
    }

}