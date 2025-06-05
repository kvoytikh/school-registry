package com.test.repository.school;

import com.test.dto.school.SchoolSearchParameters;
import com.test.model.School;
import com.test.repository.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolSpecificationBuilder implements SpecificationBuilder<School, SchoolSearchParameters> {

    private final SchoolSpecificationProviderManager schoolSpecificationProviderManager;

    @Override
    public Specification<School> build(SchoolSearchParameters searchParameters) {
        return Specification.allOf(searchParameters.getSearchParamMap().entrySet()
                .stream()
                .map(entry -> schoolSpecificationProviderManager
                        .getSpecificationProvider(entry.getKey().getColumnName())
                        .getSpecification(entry.getValue())).toList());
    }
}
