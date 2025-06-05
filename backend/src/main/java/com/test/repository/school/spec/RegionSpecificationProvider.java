package com.test.repository.school.spec;

import com.test.model.School;
import com.test.model.enums.SchoolSearchParams;
import com.test.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RegionSpecificationProvider implements SpecificationProvider<School> {

  @Override
  public String getKey() {
    return SchoolSearchParams.REGION.getColumnName();
  }

  @Override
  public Specification<School> getSpecification(Object param) {
    if (!(param instanceof String parameter)) {
      throw new IllegalArgumentException("Expected parameter of type String, but got: " +
              (param == null ? "null" : param.getClass().getSimpleName()));
    }

    return (root, query, cb) -> cb.like(cb.lower(root.get(getKey())),
            "%" + parameter.toLowerCase() + "%");
  }
}
