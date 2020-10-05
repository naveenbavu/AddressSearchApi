package com.addresssearch.addresssearch;

import com.addresssearch.addresssearch.model.Address;
import com.addresssearch.addresssearch.service.JsonToJavaMapper;
import com.addresssearch.addresssearch.util.AddrSearchUtility;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Naveen Bavu
 */
@SpringBootApplication
public class AddresssearchApplication {

  public static void main(String[] args) {
    List<Address> list = Arrays.asList(JsonToJavaMapper.read("address.json", Address[].class));
    List<Address> result = AddrSearchUtility.searchAddressFromPartialString("se", list);
    result.forEach(address ->
        System.out.println(address));
  }

}
