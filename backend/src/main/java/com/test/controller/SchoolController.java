package com.test.controller;

import com.test.dto.common.OperationResponseDto;
import com.test.dto.school.CreateSchoolRequestDto;
import com.test.dto.school.SchoolResponseDto;
import com.test.dto.school.SchoolSearchParameters;
import com.test.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {
  private final SchoolService schoolService;

  @PostMapping
  public ResponseEntity<OperationResponseDto<SchoolResponseDto>> createSchool(@RequestBody @Valid CreateSchoolRequestDto createSchoolRequestDto) {
    return new ResponseEntity<>(schoolService.save(createSchoolRequestDto), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Page<SchoolResponseDto>> getSchools(Pageable pageable) {
    return new ResponseEntity<>(schoolService.findAll(pageable), HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<Page<SchoolResponseDto>> searchSchools(SchoolSearchParameters searchParameters, Pageable pageable) {
    return new ResponseEntity<>(schoolService.search(searchParameters, pageable), HttpStatus.OK);
  }

  @PatchMapping("/{id}/deactivate")
  public ResponseEntity<OperationResponseDto<SchoolResponseDto>> deactivateSchool(@PathVariable Long id) {
    return new ResponseEntity<>(schoolService.deactivateById(id), HttpStatus.OK);
  }
}
