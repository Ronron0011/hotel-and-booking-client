package fa.training.mockproject.mockprojectfjb05group01.utilize.reenterpassword;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy=PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {
    String message() default "The re-enter password doesn't match. Please input again";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}