package com.genov.dress_me_shop.utils.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidationUtilImpl implements ValidationUtil {
	private Validator validator;
	
	public ValidationUtilImpl(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public <E> Set<ConstraintViolation<E>> getViolations(E entity) {
		return this.validator.validate(entity);
//		Set<ConstraintViolation<E>> violations = this.validator.validate(entity);
//		violations.stream().map(v -> this.generateErrorMessage(v))
//		.collect(Collectors.toList());
//		if (violations.size() > 0) {
//			Set<String> messages = new HashSet<>();
//			violations.forEach(v -> messages.add(v.getRootBeanClass().getSimpleName() + " - " + v.getPropertyPath() + " : " + v.getMessage()));
//			String exceptionMessage = String.join("\r\n", messages);
//			throw new ConstraintViolationException(exceptionMessage, violations);
//		}
	}

	@Override
	public <E> boolean isValid(E entity) {
		return this.validator.validate(entity).size() == 0;
	}

}
