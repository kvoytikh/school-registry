package com.test.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
  String getKey();

  Specification<T> getSpecification(Object params);
}
