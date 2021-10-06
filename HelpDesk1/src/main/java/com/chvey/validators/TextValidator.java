package com.chvey.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class TextValidator {
    public boolean name(String name){
        return Pattern.matches("^[-\\w\\s\"'`~.#!$%@^&*+=,:;?/()|\\[\\]{}]{1,100}$", name);
    }
    public boolean text(String text){
        return Pattern.matches("^[-\\w\\s\"'`~.#!$%@^&*+=,:;?/()|\\[\\]{}]{1,500}$", text);
    }
}
