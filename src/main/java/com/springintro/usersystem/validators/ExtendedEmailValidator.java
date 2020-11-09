package com.springintro.usersystem.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.springintro.usersystem.constants.GlobalConstants.EMAIL_REGEX;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Email(message = "Email must be a well-formed email address. Ex. 'user@host.com'")
@Pattern(regexp = EMAIL_REGEX, message = "Please provide a valid email address")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ExtendedEmailValidator {
    String message() default "Please provide a valid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
