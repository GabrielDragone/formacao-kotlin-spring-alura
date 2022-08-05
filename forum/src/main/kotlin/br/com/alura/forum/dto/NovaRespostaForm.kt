package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

data class NovaRespostaForm(
    @field:NotEmpty
    val mensagem: String,

    @field:Positive
    val idAutor: Long
)
