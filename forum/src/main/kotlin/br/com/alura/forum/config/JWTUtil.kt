package br.com.alura.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtil {

    private val expiration: Long = 60000 // 1 minuto

    @Value("\${jwt.secret}") // Está no application.yml
    private lateinit var secret: String // Inicialização preguiçosa, ou seja, só quando for chamada a generateToken

    // Verificar exemplo: https://jwt.io/
    fun generateToken(username: String): String? { // ? pode ou não retornar uma String
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration)) // Usuário vai poder trabalhar durante 1 minuto na aplicação e ao expirar, vai ser necessário se autenticar de novo
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

}