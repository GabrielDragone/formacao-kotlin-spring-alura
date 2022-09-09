package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(
    //private var usuarios: List<Usuario> // Retirado pois agora passamos a utilizar o banco H2 através do UsuarioRepository
    private val usuarioRepository: UsuarioRepository
    ): UserDetailsService { //

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

    //Quando o nosso usuário passar as informações para a nossa aplicação, nós vamos fazer o hash da senha e, com o username, vamos buscar a informação no nosso banco de dados.
    override fun loadUserByUsername(username: String?): UserDetails { // Toda requisição passará por aqui e verificará se o usuário existe no banco de dados para prosseguir:
        val usuario = usuarioRepository.findByEmail(username).orElseThrow { NotFoundException("O Usuário ou Password informados não conferem!") }
        return UserDetail(usuario)
    }


}
