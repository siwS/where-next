package com.where_next.validations;

public interface RemovalValidator {

    String NOTHING_TO_DELETE_ERROR_MSG = "Object id is null. Nothing to delete.";

    void validate(Long id);
}
