package com.addresssearch.addresssearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddresssearchApplication {


  static List<Address> searchAddressFromPartialString(String queryStr, List<Address> list) {
    List<Address> suggestion = new ArrayList<>();
    list.forEach(address -> {
      if (isMatched(queryStr, String.valueOf(address.getLine1()))) {
        suggestion.add(address);
      }
      if (isMatched(queryStr, address.getLine2())) {
        suggestion.add(address);
      }
      if (isMatched(queryStr, address.getCity())) {
        suggestion.add(address);
      }
      if (isMatched(queryStr, address.getState())) {
        suggestion.add(address);
      }
      if (isMatched(queryStr, address.getZip())) {
        suggestion.add(address);
      }
    });
    suggestion.sort(Comparator.comparing(Address::getLine1));
    return suggestion;
  }

  private static boolean isMatched(String query, String text) {
    boolean res = false;
    if (query != null && text != null) {
      res = text.toLowerCase().contains(query.toLowerCase());
    }
    return res;
  }

  public static void main(String[] args) throws JsonProcessingException {
    List<Address> list = Arrays.asList(JsonToJavaMapper.read("address.json", Address[].class));
    List<Address> result = searchAddressFromPartialString("se", list);
    result.forEach(address ->
        System.out.println(address));
  }

}
