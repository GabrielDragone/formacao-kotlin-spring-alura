package br.com.alura.forum.dto

import javax.validation.constraints.*

//data class NovoTopicoDto(
// O DTO de entrada é um Form, por isso foi alterado aqui.
// @Bean: Validações, também é necessário anotar o Endpoint com @Valid. Se houver alguma violação, o Spring vai retornar o erro 400 de bad request.
// @Field: Como estamos usando um data class e os atributos fazem parte dos parametros do construtor do data class,
// é necessário informar @Field devido à restrições que informam que essas anotações deveriam estar no metodo getter ou no atributo.
// Mais info: https://www.baeldung.com/javax-validation
data class NovoTopicoForm( // Construtor
    @field:NotEmpty // Bean Validation e Spring, validem esse campo, ele não pode ser enviado vazio "".
    @field:Size(min = 5, max = 50, message = "O titulo deve ter entre 5 e 50 caracteres!") // ... ..., ... ..., ele deve ter o tamanho entre 5 e 100 caracteres
    val titulo: String,

    @field:NotEmpty(message = "A mensagem deve estar preenchida!")
    @field:Size(min = 5, max = 100, message = "A mensagem deve ter entre 5 e 100 caracteres!")
    val mensagem: String,

    @field:NotNull(message = "O curso não pode ser nulo!") // Ben Validation e Spring, validem esse campo, ele não pode ser null.
    @field:Positive // Só pode acima de 0 (1, 2, 3, ...)
    val idCurso: Long,

    @field:NotNull(message = "O autor não pode ser nulo!")
    @field:Positive // Só pode de 0 ou 1
    val idAutor: Long
)
