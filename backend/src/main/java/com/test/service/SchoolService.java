package com.test.service;

import com.test.dto.common.OperationResponseDto;
import com.test.dto.school.CreateSchoolRequestDto;
import com.test.dto.school.SchoolResponseDto;
import com.test.dto.school.SchoolSearchParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolService {
  OperationResponseDto<SchoolResponseDto> save(CreateSchoolRequestDto schoolRequestDto);

  Page<SchoolResponseDto> findAll(Pageable pageable);

  Page<SchoolResponseDto> search(SchoolSearchParameters searchParameters, Pageable pageable);

  OperationResponseDto<SchoolResponseDto> deactivateById(Long id);
}
