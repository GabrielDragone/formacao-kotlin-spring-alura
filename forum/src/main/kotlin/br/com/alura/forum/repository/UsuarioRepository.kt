package br.com.alura.forum.repository

import br.com.alura.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

// Ler dicas em TopicoRepository.
interface UsuarioRepository: JpaRepository<Usuario, Long> {
}