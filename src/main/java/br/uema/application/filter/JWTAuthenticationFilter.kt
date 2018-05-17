package br.uema.application.filter

import java.io.IOException

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import br.uema.application.service.TokenAuthenticationService
import br.uema.application.util.ResponseStructure
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter: GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {

        try {
            val authentication = TokenAuthenticationService.getAuthentication(request as HttpServletRequest)

            SecurityContextHolder.getContext().authentication = authentication

            filterChain.doFilter(request, response)

        } catch (e: AuthenticationCredentialsNotFoundException) {
            ResponseStructure.error(HttpStatus.FORBIDDEN, e.message!!, response as HttpServletResponse)
        }
    }
}