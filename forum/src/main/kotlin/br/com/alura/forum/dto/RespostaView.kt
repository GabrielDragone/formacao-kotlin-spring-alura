package br.com.alura.forum.dto

data class RespostaView(
    val id: Long?,
    val mensagem: String,
    val solucao: Boolean,
    val autorNome: String,
    val topicoId: Long?
)
