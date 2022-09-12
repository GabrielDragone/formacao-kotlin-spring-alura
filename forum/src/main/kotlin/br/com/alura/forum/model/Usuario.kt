package br.com.alura.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Usuario (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,

    @JsonIgnore // Não mostrará as roles qnd fizer um get no usuario, pois pode ocorrer exception.
    @ManyToMany(fetch = FetchType.EAGER) // No momento que buscar o usuário, fetch EAGER já carrega todas as roles.
    @JoinColumn(name = "usuario_role") // Join com a tabela usuario_role.
    val role: List<Role> = mutableListOf()
)
