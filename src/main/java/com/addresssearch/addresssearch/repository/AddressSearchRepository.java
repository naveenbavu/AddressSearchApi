package com.addresssearch.addresssearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Naveen Bavu
 */
public interface AddressSearchRepository  extends ElasticsearchRepository<String, Addre> {



}
