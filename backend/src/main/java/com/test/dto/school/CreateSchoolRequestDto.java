package com.test.dto.school;

import com.test.model.enums.SchoolType;
import com.test.validation.school.Edrpou;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateSchoolRequestDto {
  @NotBlank
  private String name;

  @NotBlank
  private String region;

  @NotNull
  private SchoolType schoolType;

  @Edrpou
  private String edrpou;
}
