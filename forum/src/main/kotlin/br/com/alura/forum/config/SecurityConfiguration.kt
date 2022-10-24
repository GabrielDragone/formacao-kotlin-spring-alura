package br.com.alura.forum.config

import br.com.alura.forum.security.JWTAuthenticationFilter
import br.com.alura.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration // Pro framework gerenciar.
@EnableWebSecurity // Vai habilitar a segurança na aplicação. O Spring vai olhar a configuração dela quando subir o projeto
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.
        //authorizeHttpRequests()?.   // Autorize as requisições
        //csrf()?.disable()?. // O CSRF habilitado, faz com que um novo token seja gerado e validado do lado do cliente e navegador do mesmo evitando que outros usuarios consigam utilizar nossa autorização
        authorizeRequests()?.   // Autorize as requisições
        antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?. // Pra acessar tópicos é necessário da role LEITURA_ESCRITA
        //antMatchers(HttpMethod.POST,"/login")?.permitAll()?.
        anyRequest()?.              // Qualquer requisição
        authenticated()?.           // Precisa estar autenticada
        and()?.
        //http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        //http?.addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass) // Valida o token a cada requisição.
        /*http?.*/sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?. // Não guarde o estado da autenticação
        and()?.
        formLogin()?.disable()?. // Tela de Login desabilitado
        httpBasic()
    }

    // Pega as informações do usuário e passa pro método responsável que vai chamar o repository e validar se é um login valido:
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder()) // https://bcrypt-generator.com/
    }

    @Bean // Pra ser gerenciado pelo Spring.
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}