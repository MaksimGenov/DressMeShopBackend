package com.genov.dress_me_shop.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.genov.dress_me_shop.utils.validation.PasswordValidator;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
	int minLength();
	int maxLength();
	boolean conatinsDigit() default false;
	boolean containsUppercase() default false;
	boolean containsLowercase() default false;
	boolean containsSpecialSymbol() default false;
	String message() default "Invalid Password";
	
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
