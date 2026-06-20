package com.cmpt276.asn2;

import com.cmpt276.asn2.Role.*;
import com.cmpt276.asn2.Application.*;
import com.cmpt276.asn2.Service.*;
import com.cmpt276.asn2.StaffMemberProfile.ProfileInit;
import com.cmpt276.asn2.StaffMemberProfile.StaffProfile;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/ratings")
public class MainController {
    
    private final MainService service;

    public MainController(MainService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ratings", service.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("rating", service.findById(id));
        return "detail";
    }
    
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("staffRating", new StaffRating());
        model.addAttribute("roles", Role.values());
        return "create";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute StaffRating rating, BindingResult result, Model model) {
        if (service.existsByEmail(rating.getEmail())) {
            result.addError(new FieldError("rating", "email", "Email already exists"));
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("formTitle", "Create New Rating");
            model.addAttribute("actionUrl", "/ratings");
            return "form";
        }
        StaffProfile profile = new StaffProfile();
        profile.setRole(rating.getProfile().getRole());
        profile.setProfileTitle(ProfileInit.fromRole(rating.getProfile().getRole()).displayTitle());
        rating.setProfile(profile);
        StaffRating saved = service.save(rating);
        return "redirect:/ratings/" + saved.getId();
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staffRating", service.findById(id));
        model.addAttribute("roles", Role.values());
        model.addAttribute("formTitle", "Edit Rating");
        model.addAttribute("actionUrl", "/ratings/" + id);
        return "form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("staffRating") StaffRating rating, BindingResult result, Model model) {
        rating.setId(id);
        if (service.existsByEmailAndIdNot(rating.getEmail(), id)) {
            result.addError(new FieldError("rating", "email", "Email already exists"));
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            model.addAttribute("formTitle", "Edit Rating");
            model.addAttribute("actionUrl", "/ratings/" + id + "/edit");
            return "form";
        }
        StaffProfile profile = new StaffProfile();
        profile.setRole(rating.getProfile().getRole());
        profile.setProfileTitle(ProfileInit.fromRole(rating.getProfile().getRole()).displayTitle());
        rating.setProfile(profile);
        service.save(rating);
        return "redirect:/ratings/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable Long id, Model model) {
        model.addAttribute("rating", service.findById(id));
        return "delete-confirm";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/ratings";
    }

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/ratings";
    }
    
}