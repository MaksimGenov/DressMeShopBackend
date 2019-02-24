package com.genov.dress_me_shop.utils.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface ValidationUtil {

    <E> boolean isValid(E entity);
    
    <E> Set<ConstraintViolation<E>> getViolations(E entity);
}
