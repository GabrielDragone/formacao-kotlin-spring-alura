package br.com.alura.forum.security

import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.model.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtUtil: JWTUtil
) : UsernamePasswordAuthenticationFilter() {

    // Vantagem do token é, que a primeira vez que autentica e gera o token, toda vez que rolar uma requisição, o sistema não vai verificar novamente o usuário no banco de dados:
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        //return super.attemptAuthentication(request, response) // Antigo
        // Pegar a requisição e extrair o username e password:
        val(username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java) // Jackson, mapeia a request pra um objeto que precisamos
        val token = UsernamePasswordAuthenticationToken(username, password) // Autentica nosso usuario com username e password.
        return authManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        //super.successfulAuthentication(request, response, chain, authResult) // Antigo
        //val username = (authResult?.principal as UserDetails).username
        //val token = jwtUtil.generateToken(username)
        val user = (authResult?.principal as UserDetails)
        val token = jwtUtil.generateToken(user.username, user.authorities)
        response?.addHeader("Authorization", "Bearer $token")
    }

}
