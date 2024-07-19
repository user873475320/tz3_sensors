package ru.sensors.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Utils {
    public static String getReadableErrorMessage(BindingResult bindingResult) {
        StringBuilder errors = new StringBuilder();
        for(FieldError error : bindingResult.getFieldErrors()) {
            errors
                    .append(error.getField())
                    .append(": ")
                    .append(error.getDefaultMessage())
                    .append(";\n");
        }
        return errors.toString();
    }
}
