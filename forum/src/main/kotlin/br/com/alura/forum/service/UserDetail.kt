package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario): UserDetails {

    override fun getAuthorities() = usuario.role // Quando só temos um retorno, podemos resumir com o =

    // https://bcrypt-generator.com/
    override fun getPassword() = usuario.password

    override fun getUsername() = usuario.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}