package com.microservice.login.utils;

import org.springframework.stereotype.Component;

@Component
public class QueryUtils {
    
    public String parametroContemOValor(String valor){
        return new StringBuilder()
            .append('%')
            .append(valor)
            .append('%')
            .toString();
    }

    public String valorDoParametroNoComeco(String valor){
        return new StringBuilder()
            .append(valor)
            .append('%')
            .toString();

    }
    
    public String valorDoParametroNoFinal(String valor){
        return new StringBuilder()
            .append('%')
            .append(valor)
            .toString();

    }
}
