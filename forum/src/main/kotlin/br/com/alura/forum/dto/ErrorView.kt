package br.com.alura.forum.dto

import java.time.LocalDateTime

// Para montar esse DTO, tentamos remover através do Postman um registro não encontrado. O Spring retorna algo parecido com o que temos abaixo,
// mas iremos personalizar para ficar melhor a visualização, pois não terá o trace.
data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
