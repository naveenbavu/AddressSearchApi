package com.addresssearch.addresssearch.util;

import com.addresssearch.addresssearch.Address;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Naveen Bavu
 */
public class AddrSearchUtility {

  /**
   * This method will search partial string in pre defined setof addresses
   *
   * @return List of addresses
   */
  public static List<Address> searchAddressFromPartialString(String partialStr,
      List<Address> list) {
    List<Address> suggestion = new ArrayList<>();
    list.forEach(address -> {
      if (isMatched(partialStr, String.valueOf(address.getLine1()))) {
        suggestion.add(address);
      }
      if (isMatched(partialStr, address.getLine2())) {
        suggestion.add(address);
      }
      if (isMatched(partialStr, address.getCity())) {
        suggestion.add(address);
      }
      if (isMatched(partialStr, address.getState())) {
        suggestion.add(address);
      }
      if (isMatched(partialStr, address.getZip())) {
        suggestion.add(address);
      }
    });
    suggestion.sort(Comparator.comparing(Address::getLine1));
    return suggestion;
  }


  /**
   * This method will return the boolean value if the matchig partial exist in the pre defined set.
   *
   * @return true or false
   */
  public static boolean isMatched(String query, String text) {
    boolean res = false;
    if (Objects.nonNull(query) && Objects.nonNull(text)) {
      res = text.toLowerCase().contains(query.toLowerCase());
    }
    return res;
  }

}
