package br.uema.application.filter

import br.uema.application.entities.AccountCredentials
import java.io.IOException
import java.util.Collections

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.security.core.Authentication
import br.uema.application.service.TokenAuthenticationService
import br.uema.application.util.ResponseStructure
import com.fasterxml.jackson.databind.JsonMappingException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.context.SecurityContextHolder

class JWTLoginFilter (url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(AntPathRequestMatcher(url)) {

    init {
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val credentials: AccountCredentials = ObjectMapper().readValue(request.inputStream, AccountCredentials::class.java)
            return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.username, credentials.password, Collections.emptyList()))
        } catch (e: JsonMappingException) {
            throw AuthenticationCredentialsNotFoundException("Não foi possível completar requisição!")
        }
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain, auth: Authentication) {
        TokenAuthenticationService.addAuthentication(response, auth.name)
        SecurityContextHolder.getContext().authentication = auth
        ResponseStructure.success(HttpStatus.OK, HttpStatus.OK.name, response)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        ResponseStructure.error(HttpStatus.BAD_REQUEST, exception.message!!, response)
    }

}