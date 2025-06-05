package com.test.model.enums;

import lombok.Getter;

@Getter
public enum SchoolType {
  GYMNASIUM("Гімназія"),
  LYCEUM("Ліцей"),
  ZZSO("ЗЗСО");

  private final String name;

  SchoolType(String name) {
    this.name = name;
  }
}
