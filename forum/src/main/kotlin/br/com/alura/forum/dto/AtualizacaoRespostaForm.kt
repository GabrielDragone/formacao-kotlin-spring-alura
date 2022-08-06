package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

data class AtualizacaoRespostaForm(
    @field:Positive
    val id: Long,
    @field:NotEmpty
    val mensagem: String
)
