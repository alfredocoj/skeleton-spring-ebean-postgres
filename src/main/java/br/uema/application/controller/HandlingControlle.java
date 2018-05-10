package br.uema.application.controller;

import br.uema.application.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HandlingControlle {

    /*public ResponseEntity<Response> generic(HttpServletRequest request, Exception exception){
        return ResponseStructure.error(exception);
    }*/
}
