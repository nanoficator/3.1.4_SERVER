package crud.controller;

import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class MainController {

    private UserService userService;
    private AuthorityService authorityService;

    public MainController(UserService userService,
                          AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public String mainPage(Model model) {
        User authUser;
        try {
            authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException ex) {
            return "redirect:/login";
        }
        boolean isAdmin = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_ADMIN"));
        boolean isUser = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_USER"));
        if (isAdmin) {
            model.addAttribute("allUsers", userService.getAllUsers());
        }
        model.addAttribute("allAuthorities", authorityService.getAllAuthorities());
        model.addAttribute("newUser", new User());
        model.addAttribute("authUser", authUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        return "mainPage";
    }

}
