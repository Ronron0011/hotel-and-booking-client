package fa.training.mockproject.mockprojectfjb05group01.utilize.phone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneConstraint {
    String message() default "The phone number is not valid. Please input again.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

