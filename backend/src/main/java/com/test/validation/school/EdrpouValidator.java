package com.test.validation.school;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EdrpouValidator implements ConstraintValidator<Edrpou, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    return s != null && s.matches("\\d{8}");
  }
}
