package com.test.repository.school;

import com.test.model.School;
import com.test.repository.SpecificationProvider;
import com.test.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchoolSpecificationProviderManager implements SpecificationProviderManager<School>{
  private final List<SpecificationProvider<School>> schoolSpecificationProviders;

  @Override
  public  SpecificationProvider<School> getSpecificationProvider(String key) {
    return schoolSpecificationProviders.stream()
            .filter(provider -> provider.getKey().equals(key))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No specification provider "
                    + "found for key: " + key));
  }
}
