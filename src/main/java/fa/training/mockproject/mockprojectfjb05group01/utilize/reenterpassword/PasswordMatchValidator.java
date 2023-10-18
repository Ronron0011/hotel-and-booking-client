package fa.training.mockproject.mockprojectfjb05group01.utilize.reenterpassword;

import fa.training.mockproject.mockprojectfjb05group01.dto.request.RegisterRequestDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, String> {

    private RegisterRequestDTO registerData;

    @Override
    public void initialize(PasswordMatch p) {
        ConstraintValidator.super.initialize(p);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String password = registerData.getPassword();
        return password.equals(value);
    }
}
