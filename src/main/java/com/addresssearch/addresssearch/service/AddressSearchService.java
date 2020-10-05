package com.addresssearch.addresssearch.service;

import com.addresssearch.addresssearch.model.Address;
import com.addresssearch.addresssearch.repository.AddressRepository;
import com.addresssearch.addresssearch.util.JsonToJavaMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author Naveen Bavu
 */
@Service
public class AddressSearchService {

  @Autowired private AddressRepository addressRepository;

  public void loadAddress() {
    List<Address> list = Arrays.asList(JsonToJavaMapper.read("address.json", Address[].class));
    list.parallelStream().forEach(address -> {
      addressRepository.save(address);
    });
  }

  public Page<Address> search(String keyWord, Map<String, String> filters, Pageable pageable) {
    BoolQueryBuilder searchQuery = QueryBuilders.boolQuery();
    if (!Objects.isNull(keyWord)) {
      searchQuery.should(
          QueryBuilders.queryStringQuery("*" + keyWord + "*")
              .defaultOperator(Operator.AND)
              .analyzeWildcard(true));
    }
    prepareFilters(searchQuery, filters);
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    queryBuilder
        .withQuery(searchQuery)
        .withPageable(pageable)
        .withSearchType(SearchType.DFS_QUERY_THEN_FETCH);
    return addressRepository.search(queryBuilder.build());
  }

  private void prepareFilters(BoolQueryBuilder searchQuery, Map<String, String> filters) {
    if (filters == null) {
      return;
    }
    filters.entrySet().stream()
        .forEach(
            stringStringEntry -> {
              BoolQueryBuilder bool = QueryBuilders.boolQuery();
              bool.should(
                  QueryBuilders.matchQuery(
                      stringStringEntry.getKey(), stringStringEntry.getValue()));
              searchQuery.must(bool);
            });
  }

  public void clear() {
    addressRepository.deleteAll();
  }
}
