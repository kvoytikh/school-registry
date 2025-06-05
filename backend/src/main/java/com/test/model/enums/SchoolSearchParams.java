package com.test.model.enums;

import lombok.Getter;

@Getter
public enum SchoolSearchParams {
  IS_ACTIVE("isActive"),
  REGION("region"),
  TYPE("type");

  private final String columnName;

  SchoolSearchParams(String columnName) {
    this.columnName = columnName;
  }
}
