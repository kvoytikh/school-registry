package com.test.repository.school.spec;

import com.test.model.School;
import com.test.model.enums.SchoolSearchParams;
import com.test.model.enums.SchoolType;
import com.test.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SchoolTypeSpecificationProvider implements SpecificationProvider<School> {

  @Override
  public String getKey() {
    return SchoolSearchParams.TYPE.getColumnName();
  }

  @Override
  public Specification<School> getSpecification(Object param) {
    if (!(param instanceof SchoolType type)) {
      throw new IllegalArgumentException("Expected parameter of type SchoolType, but got: " +
              (param == null ? "null" : param.getClass().getSimpleName()));
    }

    return (root, query, cb) -> cb.equal(root.get(getKey()), type);
  }
}
