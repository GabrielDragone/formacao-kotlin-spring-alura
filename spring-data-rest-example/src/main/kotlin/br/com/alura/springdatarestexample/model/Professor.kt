package br.com.alura.springdatarestexample.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Professor (
    @Id @GeneratedValue
    val id: Long? = null,
    val nome: String? = null,
    val sobrenome: String? = null,
    val email: String? = null
)