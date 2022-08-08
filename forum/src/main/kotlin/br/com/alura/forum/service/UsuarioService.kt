package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    //private var usuarios: List<Usuario> // Retirado pois agora passamos a utilizar o banco H2 através do UsuarioRepository
    private val usuarioRepository: UsuarioRepository
    ) {

    // Retirado pois agora passamos a utilizar o banco H2 através do UsuarioRepository
    /*init {
        val usuario = Usuario(
            id = 1,
            nome = "Gabriel Alves",
            email = "gabriel.alves@email.com"
        )

        usuarios = Arrays.asList(usuario)
    }*/

    fun buscarPorId(id: Long): Usuario {
        // Alterado pois agora passamos a utilizar o banco H2 através do UsuarioRepository
        /*return usuarios.stream().filter(
            { u -> u.id == id }
        ).findFirst().orElseThrow { NotFoundException("Usuário não encontrado!") }*/
        return usuarioRepository.getOne(id)
    }

}
