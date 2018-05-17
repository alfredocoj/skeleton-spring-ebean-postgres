package br.uema.application.util

import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

class Response() {

    var meta : Map<String, Any?>? = null
    var data : Any? = null

    constructor(status: HttpStatus, message: String?, data: Any?) : this() {

        val authentication: Authentication? = SecurityContextHolder.getContext().authentication

        var authorization = authentication?.credentials

        this.meta = hashMapOf("code" to status.value(), "message" to message, "authorization" to authorization)
        this.data = data
    }
}