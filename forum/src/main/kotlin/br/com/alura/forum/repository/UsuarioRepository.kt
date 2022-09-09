package br.com.alura.forum.repository

import br.com.alura.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

// Ler dicas em TopicoRepository.
interface UsuarioRepository: JpaRepository<Usuario, Long> {

    // Não precisamos criar uma query nativa, a JPA faz: select * from usuario where email = username
    fun findByEmail(username: String?): Optional<Usuario>

}