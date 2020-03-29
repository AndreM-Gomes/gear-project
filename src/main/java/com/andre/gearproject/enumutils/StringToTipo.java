package com.andre.gearproject.enumutils;

import com.andre.gearproject.produto.Tipo;
import org.springframework.core.convert.converter.Converter;


public class StringToTipo implements Converter<String, Tipo> {

    @Override
    public Tipo convert(String origem) {
        try{
            return Tipo.valueOf(origem.toUpperCase());
        }catch (IllegalArgumentException e ){
            return null;
        }
    }
}
