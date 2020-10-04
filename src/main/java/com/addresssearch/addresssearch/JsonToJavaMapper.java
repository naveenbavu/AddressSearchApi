package com.addresssearch.addresssearch;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.util.ResourceUtils;


public class JsonToJavaMapper {
    public static <T> T read(String file, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T obj = null;
        try {
            obj = mapper.readValue(ResourceUtils.getFile("classpath:" + file), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String read(String file) {
        JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        try {
            return parser.parse(new FileReader(ResourceUtils.getFile("classpath:" + file))).toString();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
