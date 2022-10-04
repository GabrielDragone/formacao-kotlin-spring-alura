package br.com.alura.forum.security

import br.com.alura.forum.config.JWTUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Pegar o token
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwtUtil.isValid(jwt)) {
            val authentication = jwtUtil.getAuthentication(jwt) // Pega usuario logado e coloca o usuario no contexto da autenticação da aplicação.
            SecurityContextHolder.getContext().authentication = authentication // Seta o usuário no contexto da aplicação
        }
        filterChain.doFilter(request, response) // Faz o filtro da aplicação
    }

    private fun getTokenDetail(token: String?): String? {
        return token.let { // let: Se não for nulo, fazemos as operações, caso contrário, não fazemos nada:
            jwt ->
            jwt?.startsWith("Bearer ") // Se o jwt começar com Bearer
            jwt?.substring(7, jwt.length) // Então pegar a partir da setima casa até o final e retornar
        } ?: null
    }

}
