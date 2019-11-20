package com.parsing.gosuslug.parsgosuslug.config;

import com.parsing.gosuslug.parsgosuslug.controller.ParsingSite;
import com.parsing.gosuslug.parsgosuslug.parsingsite.ParsingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean(name = "ParsingSite")
    public ParsingSite getParsingSite(){
        return new ParsingSite();
    }

    @Bean(name = "ParsingUrl")
    public ParsingUrl getParsingUrl(){
        return new ParsingUrl();
    }

}
