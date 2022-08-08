package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity // Anota a classe como uma Entidade da JPA, mapeando uma tabela no banco de dados.
data class Resposta (
    @Id // Informa à JPA que o campo id é uma ID no Banco de Dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Informa à JPA que è a aplicação que vai gerenciar os Id's e não o banco de dados.
    var id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val autor: Usuario,
    @ManyToOne
    var topico: Topico?, // Permite que seja null
    val solucao: Boolean = false
)
