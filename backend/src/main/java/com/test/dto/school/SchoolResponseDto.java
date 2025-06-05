package com.test.dto.school;

import com.test.model.enums.SchoolType;

public record SchoolResponseDto(Long id, String name, SchoolType type, String region,
                                String edrpou, Boolean isActive) {
}
