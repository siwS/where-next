package com.where_next.validations;

public interface BaseValidator<T> {

    String REQUIRED_PARAMETERS_MISSING_ERROR_MSG = "One or more required parameters are missing.";
    String REQUIRED_PARAMETER_NAME_MISSING_ERROR_MSG = "Required parameter 'name' is missing.";
    String ENTITY_WITH_NAME_EXISTS_ERROR_MSG = "%s with name: '%s' already exists.";
    String ENTITY_WITH_ID_NOT_FOUND_ERROR_MSG = "%s with id %d not found.";
    String ENTITY_WITH_NAME_NOT_FOUND_ERROR_MSG = "%s with name '%s' not found.";

    void validate(T type);

    String type();
}
