package com.genov.dress_me_shop.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.genov.dress_me_shop.utils.validation.MyEmailValidator;

@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = MyEmailValidator.class)
public @interface Email {
	String message() default "Invalid email!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
