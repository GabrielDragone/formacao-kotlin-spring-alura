package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private var usuarios: List<Usuario>) {

    init {
        val usuario = Usuario(
            id = 1,
            nome = "Gabriel Alves",
            email = "gabriel.alves@email.com"
        )

        usuarios = Arrays.asList(usuario)
    }

    fun buscarPorId(id: Long): Usuario {
        return usuarios.stream().filter(
            { u -> u.id == id }
        ).findFirst().orElseThrow { NotFoundException("Usuário não encontrado!") }
    }

}
