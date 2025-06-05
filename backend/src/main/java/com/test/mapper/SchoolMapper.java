package com.test.mapper;

import com.test.dto.school.CreateSchoolRequestDto;
import com.test.dto.school.SchoolResponseDto;
import com.test.model.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

  public School toEntity(CreateSchoolRequestDto createSchoolRequestDto) {
    School school = new School();
    school.setName(createSchoolRequestDto.getName());
    school.setType(createSchoolRequestDto.getSchoolType());
    school.setEdrpou(createSchoolRequestDto.getEdrpou());
    school.setRegion(createSchoolRequestDto.getRegion());
    return school;
  }

  public SchoolResponseDto toDto(School school) {
    return new SchoolResponseDto(school.getId(), school.getName(), school.getType(),
            school.getRegion(), school.getEdrpou(), school.getIsActive());
  }
}
