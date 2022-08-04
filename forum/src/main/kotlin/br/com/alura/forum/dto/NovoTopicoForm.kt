package br.com.alura.forum.dto

//data class NovoTopicoDto(
data class NovoTopicoForm( // O DTO de entrada é um Form, por isso foi alterado aqui.
    val titulo: String,
    val mensagem: String,
    val idCurso: Long,
    val idAutor: Long
)
