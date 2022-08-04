package br.com.alura.forum.dto

import br.com.alura.forum.enum.StatusTopico
import java.time.LocalDateTime

class TopicoView(
    val id: Long?, // ? significa que é opcional
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)
