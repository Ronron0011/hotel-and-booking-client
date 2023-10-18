package fa.training.mockproject.mockprojectfjb05group01.utilize.password;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "The password is not valid. Please input again.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

