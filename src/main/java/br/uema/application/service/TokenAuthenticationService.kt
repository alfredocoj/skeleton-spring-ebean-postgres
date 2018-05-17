package br.uema.application.service

import java.util.*
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class TokenAuthenticationService {

    companion object {

        // EXPIRATION_TIME = 10 dias
        val EXPIRATION_TIME: Long = 860000000
        val SECRET: String = "MySecret"
        val HEADER_STRING: String = "Authorization"

        fun addAuthentication(response: HttpServletResponse, username: String) {

            val JWT = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()

            response.addHeader(HEADER_STRING, JWT)
        }

        fun getAuthentication(request: HttpServletRequest): Authentication? {
            val token = request.getHeader(HEADER_STRING)

            if (token == null || token.trim().isEmpty()) {
                return null
            }

            val user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .body
                    .subject

            if (user != null) {
                return UsernamePasswordAuthenticationToken(user, token, Collections.emptyList())
            }

            return null
        }
    }
}