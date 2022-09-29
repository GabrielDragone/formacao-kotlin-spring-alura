package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration // Pro framework gerenciar.
@EnableWebSecurity // Vai habilitar a segurança na aplicação. O Spring vai olhar a configuração dela quando subir o projeto
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService
    private val jwtUtil: JWTUtil
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.
        //authorizeHttpRequests()?.   // Autorize as requisições
        authorizeRequests()?.   // Autorize as requisições
        //antMatchers("/topicos")?.hasAuthority("LEITURA_ESCRITA")?. // Pra acessar tópicos é necessário da role LEITURA_ESCRITA
        antMatchers("/login")?.permitAll()?.
        anyRequest()?.              // Qualquer requisição
        authenticated()?.           // Precisa estar autenticada
        and()
        http?.addFilterBefore(JWTLoginFilter(authManager = authenticationManager(), jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
        http?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?. // Não guarde o estado da autenticação
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