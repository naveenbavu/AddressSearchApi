package com.addresssearch.addresssearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/** @author Naveen Bavu */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "addresses", type = "address", createIndex = true)
public class Address {

  @Id
  private String id;
  private String line1;
  private String line2;
  private String city;
  private String state;
  private String zip;
}
