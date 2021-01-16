package com.example.odwsi.odwsi.service.user;

import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.repository.user.UserRepository;
import com.example.odwsi.odwsi.service.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;

    @Override
    public boolean supports(@NonNull Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(@NonNull Object object, @NonNull Errors errors) {
        User user = (User) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirmation", "NotEmpty");

        if (user.getUsername() == null || user.getPassword() == null || user.getPasswordConfirmation() == null) return;

        if (!isUsernameValid(user.getUsername())) {
            handleInvalidUsername(errors);
        }
        if (!isUsernameAvailable(user.getUsername())) {
            handleNotAvailableUsername(errors);
        }

        if (!isPasswordValid(user.getPassword())) {
            handleInvalidPassword(errors);
        }

        if (!isPasswordConfirmationValid(user.getPassword(), user.getPasswordConfirmation())) {
            handleInvalidPasswordConfirmation(errors);
        }
    }

    private boolean isUsernameValid(String username) {
        return username.length() >= 4 && username.length() <= 64;
    }

    private void handleInvalidUsername(Errors errors) {
        errors.rejectValue("username", "Username.static");
    }

    @Transactional
    protected boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    private void handleNotAvailableUsername(Errors errors) {
        errors.rejectValue("username", "Username.dynamic");
    }

    private boolean isPasswordValid(String password) {
        return passwordValidator.validatePassword(password);
    }

    private void handleInvalidPassword(Errors errors) {
        errors.rejectValue("password", "Password.static");
    }

    private boolean isPasswordConfirmationValid(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    private void handleInvalidPasswordConfirmation(Errors errors) {
        errors.rejectValue("passwordConfirmation", "PasswordConfirmation.static");
    }
}
