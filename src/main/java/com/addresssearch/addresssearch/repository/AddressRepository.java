package com.addresssearch.addresssearch.repository;

import com.addresssearch.addresssearch.model.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Naveen Bavu
 */
@Repository
public interface AddressRepository extends ElasticsearchRepository<Address, String> {

}
