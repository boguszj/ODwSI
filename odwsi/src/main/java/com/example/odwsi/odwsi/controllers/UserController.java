package com.example.odwsi.odwsi.controllers;

import com.example.odwsi.odwsi.data.user.LoginDto;
import com.example.odwsi.odwsi.security.LoginErrorCode;
import com.example.odwsi.odwsi.service.user.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import com.example.odwsi.odwsi.model.User;
import com.example.odwsi.odwsi.service.user.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserValidator userValidator;
    private final UserRegistrationService userRegistrationService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userRegistrationService.registerUser(user);

        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginDto loginDto, BindingResult bindingResult, String error) {

        if (error != null && error.equals(LoginErrorCode.INVALID))
            bindingResult.rejectValue("error", "Auth.invalid");
        if (error != null && error.equals(LoginErrorCode.BLOCKED))
            bindingResult.rejectValue("error", "Auth.blocked");

        return "login";
    }
}
