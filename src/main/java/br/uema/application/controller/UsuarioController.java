package br.uema.application.controller;

import io.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    @Qualifier("ebeanServer")
    private EbeanServer server;

    @GetMapping(value = "teste")
    public String teste(){
        return "OK!";
    }

}