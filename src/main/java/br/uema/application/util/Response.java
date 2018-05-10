package br.uema.application.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

public class Response {

    private Map<String, Object> meta;

    private Object data;

    public Response(HttpStatus status, String message, Object data){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", status.value());
        map.put("message", message);
        map.put("authorization", authentication.getCredentials());

        this.meta = map;
        this.data = data;

    }
}
