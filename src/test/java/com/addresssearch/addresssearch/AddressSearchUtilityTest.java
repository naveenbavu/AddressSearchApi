package com.addresssearch.addresssearch;

import com.addresssearch.addresssearch.model.Address;
import com.addresssearch.addresssearch.util.AddrSearchUtility;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AddressSearchUtilityTest {

  List<Address> addresses = null;


  @Before
  public void setup() {
    addresses = Arrays.asList(new Address("1600 Holloway Ave", "Suite 10",
            "San Francisco", "CA", "94536")
        , new Address("1600 Holloway Ave", "Suite 20",
            "San Francisco", "MA", "94536")
        , new Address("1600 Holloway Ave", "Suite 30",
            "San Francisco", "BO", "94536"),
        new Address("3355 Pennslvania Ave", "Apt 9",
            "Fremont", "CA", "94536"));
  }

  @Test
  public void AddrSearchMatchingAddressTest() {
    Assert.assertEquals(3,
        AddrSearchUtility.searchAddressFromPartialString("1600", addresses).size());
  }

  @Test
  public void AddrSearchNonMatchingAddressTest() {
    Assert.assertEquals(0,
        AddrSearchUtility.searchAddressFromPartialString("1234", addresses).size());
  }

  @Test
  public void AddrSearchMatchingStateTest() {
    Assert.assertEquals(2,
        AddrSearchUtility.searchAddressFromPartialString("CA", addresses).size());
  }
}
