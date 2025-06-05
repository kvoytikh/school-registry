package com.test.service.impl;

import com.test.dto.common.OperationResponseDto;
import com.test.dto.school.CreateSchoolRequestDto;
import com.test.dto.school.SchoolResponseDto;
import com.test.dto.school.SchoolSearchParameters;
import com.test.mapper.SchoolMapper;
import com.test.model.School;
import com.test.repository.school.SchoolRepository;
import com.test.repository.school.SchoolSpecificationBuilder;
import com.test.service.SchoolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;
    private final SchoolSpecificationBuilder schoolSpecificationBuilder;

    @Override
    public OperationResponseDto<SchoolResponseDto> save(CreateSchoolRequestDto schoolRequestDto) {
        School entity = schoolRepository.save(schoolMapper.toEntity(schoolRequestDto));
        return new OperationResponseDto<>(true, schoolMapper.toDto(entity));
    }

    @Override
    public Page<SchoolResponseDto> findAll(Pageable pageable) {
        return schoolRepository.findAll(pageable).map(schoolMapper::toDto);
    }

    @Override
    public Page<SchoolResponseDto> search(SchoolSearchParameters searchParameters, Pageable pageable) {
        Specification<School> specification = schoolSpecificationBuilder.build(searchParameters);
        return schoolRepository.findAll(specification, pageable)
                .map(schoolMapper::toDto);
    }

    @Override
    public OperationResponseDto<SchoolResponseDto> deactivateById(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("School not found with id: " + id));

        school.setIsActive(false);
        school = schoolRepository.save(school);
        return new OperationResponseDto<>(true, schoolMapper.toDto(school));
    }
}
