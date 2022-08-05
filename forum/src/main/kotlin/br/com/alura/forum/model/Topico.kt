package br.com.alura.forum.model

import br.com.alura.forum.enum.StatusTopico
import java.time.LocalDateTime

data class Topico (
    var id: Long? = null, // ? retorna null em caso de falha de conversão. var é variavel, ou seja, muda, ava???
    val titulo: String, // val é constante, ou seja, esse valor nunca vai mudar
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(), // No momento da criação já pega a data.
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO, // Enum, qnd criar já cria como não respondido.
    val resposta: List<Resposta> = ArrayList()
)