package com.petronilho.taskapi.task;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * Created by renanpetronilho on 24/07/17.
 */
public class TaskException extends ValidationException {


    private static final long serialVersionUID = 1L;

    private Set<ConstraintViolation<Task>> constraintViolations;

    public TaskException(Set<ConstraintViolation<Task>> constraintViolations) {
        super();
        this.setConstraintViolations(constraintViolations);
    }

    public Set<ConstraintViolation<Task>> getConstraintViolations() {
        return constraintViolations;
    }

    public void setConstraintViolations(Set<ConstraintViolation<Task>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }
}
