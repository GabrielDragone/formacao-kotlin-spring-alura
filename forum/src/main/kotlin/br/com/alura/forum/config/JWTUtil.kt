package br.com.alura.forum.config

import br.com.alura.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil(
    private val usuarioService: UsuarioService // Pra buscar as roles do usuario
) {

    private val expiration: Long = 60000 // 1 minuto

    @Value("\${jwt.secret}") // Está no application.yml
    private lateinit var secret: String // Inicialização preguiçosa, ou seja, só quando for chamada a generateToken

    // Verificar exemplo: https://jwt.io/
    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? { // ? pode ou não retornar uma String
        try {
            return Jwts.builder()
                .setSubject(username)
                .claim("role", authorities) // Seta o que o usuário tem acesso.
                .setExpiration(Date(System.currentTimeMillis() + expiration)) // Usuário vai poder trabalhar durante 1 minuto na aplicação e ao expirar, vai ser necessário se autenticar de novo
                .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
                .compact()
        } catch (e: java.lang.Exception) {
            print("Erro:" + e)
            return null
        }
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJwt(jwt)
            true
        } catch (e: java.lang.IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwt).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }

}