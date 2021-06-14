package com.chvey.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final String DB_PROPS_PATH = "src/main/resources/db/db.properties";

    private static String  prop(String s) {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(DB_PROPS_PATH);
            property.load(fis);
            return property.getProperty(s);
        } catch (IOException e) {
            return "Property not found";
        }
    }
    public static  String getDbDriver (){
        return prop("db.driver");
    }
    public static String getDbURL(){
        return  prop("db.url");
    }
    public static String getLogin(){
        return prop("login");
    }
    public static String getPassword(){
        return prop("password");
    }
}
