
package com.addresssearch.addresssearch.controller;

import com.addresssearch.addresssearch.model.Address;
import com.addresssearch.addresssearch.service.AddressSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Naveen Bavu
 */

@RestController
@RequestMapping(value = "/api/v1" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
@Api(value = "/api/v1" , description = "Address Search API" , tags = "Address Search")
public class AddressSearchController {

  @Inject
  AddressSearchService addressSearchService;


  @GetMapping("/getAddress")
  @ApiOperation(value = "Retrieve Address Details",
  notes = "GetAddressSearchRequest",
  response = Address.class,
  responseContainer = "ResponseEntity")
  @ApiResponses(
      value = {
          @ApiResponse(
              code = 200,
              message = "Succesfully received Address Details",
              response = Address.class
          )
      }
  )
  public Page<Address> getAddressDetails(
      @RequestParam(value = "q", required = false) String query,

      @PageableDefault(value = 10, page = 0) Pageable pageable,
      @MatrixVariable(value = "filter", required = false) Map<String, String> filter
  ){
    return addressSearchService.search(query,filter,pageable);
  }

}

