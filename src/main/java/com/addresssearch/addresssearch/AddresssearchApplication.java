package com.addresssearch.addresssearch;

import com.addresssearch.addresssearch.service.AddressSearchService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author Naveen Bavu
 */
@SpringBootApplication
@EnableSwagger2WebMvc
public class AddresssearchApplication {
  @Autowired
  private AddressSearchService addressSearchService;

  public static void main(String[] args) {
    SpringApplication.run(AddresssearchApplication.class, args);

  }

  @PostConstruct
  public void loadData() {
    addressSearchService.clear();
    addressSearchService.loadAddress();
  }

}
