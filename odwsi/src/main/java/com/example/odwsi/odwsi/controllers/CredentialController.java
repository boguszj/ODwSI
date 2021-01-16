package com.example.odwsi.odwsi.controllers;

import com.example.odwsi.odwsi.data.credential.CredentialDto;
import com.example.odwsi.odwsi.data.credential.ShareCredentialDto;
import com.example.odwsi.odwsi.service.credential.CredentialService;
import com.example.odwsi.odwsi.service.credential.CredentialValidator;
import com.example.odwsi.odwsi.service.credential.ShareCredentialDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialService credentialService;
    private final CredentialValidator credentialValidator;
    private final ShareCredentialDtoValidator shareCredentialDtoValidator;

    @GetMapping({"/", "/dashboard"})
    public ModelAndView listCredentials() {
        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("credentials", credentialService.getCredentialsForCurrentUser());
        return modelAndView;
    }

    @GetMapping("/create")
    public String createCredentialsForm(Model model) {
        model.addAttribute("credentialForm", new CredentialDto());
        return "create";
    }

    @PostMapping("/create")
    public String createCredential(@Valid @ModelAttribute("credentialForm") CredentialDto credentialDto, BindingResult bindingResult) {

        credentialValidator.validate(credentialDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            credentialService.saveCredential(credentialDto);
            return "redirect:/dashboard";
        }

    }

    @GetMapping("/details/{credentialId}")
    @PreAuthorize("@credentialAuthorizationProvider.authorizedToCredential(#credentialId)")
    public ModelAndView getDetails(@PathVariable UUID credentialId, Model model) {
        model.addAttribute("shareCredentialForm", new ShareCredentialDto());
        return getDetailsModelAndView(credentialId);
    }

    @DeleteMapping("/details/{credentialId}")
    @PreAuthorize("@credentialAuthorizationProvider.ownerOfCredential(#credentialId)")
    public String deleteCredential(@PathVariable UUID credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/dashboard";
    }

    @PostMapping("/details/{credentialId}")
    @PreAuthorize("@credentialAuthorizationProvider.ownerOfCredential(#credentialId)")
    public ModelAndView shareCredential(@PathVariable UUID credentialId, @Valid @ModelAttribute("shareCredentialForm") ShareCredentialDto shareCredentialDto, BindingResult bindingResult) {
        shareCredentialDto.setCredentialId(credentialId);
        shareCredentialDtoValidator.validate(shareCredentialDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            credentialService.shareCredential(shareCredentialDto);
        }
        return getDetailsModelAndView(credentialId);
    }

    private ModelAndView getDetailsModelAndView(UUID credentialId) {
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("credential", credentialService.getCredentialVaultEntity(credentialId));
        return modelAndView;
    }

}
