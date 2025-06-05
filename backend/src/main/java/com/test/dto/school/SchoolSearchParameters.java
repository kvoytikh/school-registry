package com.test.dto.school;

import com.test.model.enums.SchoolSearchParams;
import com.test.model.enums.SchoolType;
import java.util.HashMap;
import java.util.Map;

import static com.test.model.enums.SchoolSearchParams.*;

public record SchoolSearchParameters(String region, SchoolType schoolType, Boolean isActive) {

    public Map<SchoolSearchParams, Object> getSearchParamMap() {
        Map<SchoolSearchParams, Object> searchParams = new HashMap<>();
        addIfNotNull(searchParams, REGION, region);
        addIfNotNull(searchParams, TYPE, schoolType);
        addIfNotNull(searchParams, IS_ACTIVE, isActive);
        return searchParams;
    }

    private void addIfNotNull(Map<SchoolSearchParams, Object> searchParams, SchoolSearchParams key, Object value) {
        if (value != null) {
            searchParams.put(key, value);
        }
    }
}