package br.com.alura.forum.repository

import br.com.alura.forum.model.Curso
import org.springframework.data.jpa.repository.JpaRepository

// Ler dicas em TopicoRepository.
interface CursoRepository: JpaRepository<Curso, Long> {
}