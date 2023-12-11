package com.pmmnm.StudentManagement.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
