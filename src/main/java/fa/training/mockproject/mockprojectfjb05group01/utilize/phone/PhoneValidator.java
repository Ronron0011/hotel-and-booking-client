package fa.training.mockproject.mockprojectfjb05group01.utilize.phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, String> {
    @Override
    public void initialize(PhoneConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // Check Vietnam phone number
        if (s == null) {
            return false;
        }
        return s.matches("^(\\+84|84|0[35789])(-?\\d{8,10})$");
    }
}