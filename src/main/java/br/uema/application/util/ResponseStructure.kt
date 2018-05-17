package br.uema.application.util

import org.springframework.http.MediaType
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import com.fasterxml.jackson.databind.ObjectMapper
import br.uema.application.exception.GenericException

class ResponseStructure {

    companion object {

        protected fun response(response: Response, status: HttpStatus): ResponseEntity<Response> {
            return ResponseEntity.status(status).body(response)
        }

        fun success(data: Any): ResponseEntity<Response> {
            val status = HttpStatus.OK
            val response = Response(status, status.reasonPhrase, data)
            response.data = data
            return response(response, status)
        }

        fun success(data: Any, status: HttpStatus): ResponseEntity<Response> {
            val response = Response(status, status.reasonPhrase, data)
            return response(response, status)
        }

        fun success(message: String): ResponseEntity<Response> {
            val status = HttpStatus.OK
            val response = Response(status, message, null)
            return response(response, status)
        }

        fun success(message: String, status: HttpStatus): ResponseEntity<Response> {
            val response = Response(status, message, null)
            return response(response, status)
        }

        fun success(status: HttpStatus, data: Any, response: HttpServletResponse): ResponseEntity<Response> {
            val success: ResponseEntity<Response> = success(data, status)
            processJsonResponse(response, success.body, status)
            return success
        }

        fun error(status: HttpStatus, message: String): ResponseEntity<Response> {
            val response = Response(status, message, null)
            return response(response, status)
        }

        fun error(status: HttpStatus, exception: GenericException): ResponseEntity<Response> {
            val response = Response(status, exception.statusText, null)
            return response(response, status)
        }

        fun error(exception: GenericException): ResponseEntity<Response> {
            val status = exception.statusCode
            val response = Response(status, exception.statusText, null)
            return response(response, status)
        }

        fun error(message: String): ResponseEntity<Response> {
            val status = HttpStatus.BAD_REQUEST
            val response = Response(status, message, null)
            return response(response, status)
        }

        fun error(status: HttpStatus, message: String, response: HttpServletResponse): ResponseEntity<Response> {
            val error: ResponseEntity<Response> = error(status, message)
            processJsonResponse(response, error.body, status)
            return error
        }

        private fun processJsonResponse(response: HttpServletResponse, body: Any, status: HttpStatus) {
            response.status = status.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.writer.write(ObjectMapper().writeValueAsString(body))
            response.writer.flush()
        }
    }

}