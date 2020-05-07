package crud.controller;

import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class MainController {

    private UserService userService;
    private AuthorityService authorityService;
    private PasswordEncoder passwordEncoder;

    public MainController(UserService userService,
                          AuthorityService authorityService,
                          @Lazy PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String mainPage(Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_ADMIN"));
        boolean isUser = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_USER"));
        if (isAdmin) {
            model.addAttribute("allUsers", userService.getAllUsers());
        }
        model.addAttribute("allAuthorities", authorityService.getAllAuthorities());
        model.addAttribute("newUser", new User());
        model.addAttribute("editUser", new User());
        model.addAttribute("authUser", authUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        return "mainPage";
    }

}
