package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class NovaRespostaForm(
    @field:NotEmpty(message = "A mensagem não pode ser vazia!") // Mensagem personalizada caso o cliente não digite de acordo com regras.
    @field:Size(min = 5, max = 100, message = "A mensagem deve ter entre 5 e 100 caracteres!")
    val mensagem: String,

    @field:Positive
    val idAutor: Long
)
