package br.uema.application.controllers;

import br.uema.application.entities.Usuario;
import br.uema.application.entities.query.QUsuario;
import io.ebean.Ebean;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public Object list(){
        return new QUsuario().findList();
    }

    @PostMapping
    public Object save(@RequestBody Usuario usuario){
        try {
            usuario.save();
            return usuario;
        } catch (Exception e){
            return "Erro ao salvar";
        }
    }
}
