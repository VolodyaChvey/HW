package com.chvey.validator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class Validator {
    public boolean validateProducts(String products) {
        if (products == null) {
            return false;
        }
        if(products.isEmpty()){
            return false;
        }
        List<String> productsListString = Arrays.asList(products.split(","));

        return true;
    }
}
