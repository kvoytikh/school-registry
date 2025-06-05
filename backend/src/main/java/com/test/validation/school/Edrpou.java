package com.test.validation.school;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EdrpouValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Edrpou {
  String message() default "Invalid edrpou";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
