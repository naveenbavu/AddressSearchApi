package com.addresssearch.addresssearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Naveen Bavu
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "addresses", type = "address")
public class Address {

  @Id
  String id;
  private String line1;
  private String line2;
  private String city;
  private String state;
  private String zip;


}
