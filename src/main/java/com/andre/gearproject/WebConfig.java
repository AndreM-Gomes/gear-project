package com.andre.gearproject;

import com.andre.gearproject.enumutils.StringToTipo;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new StringToTipo());
    }
}
