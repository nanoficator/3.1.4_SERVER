package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping("/admin/table")
    public String tablePage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "tablePage";
    }

    @GetMapping("/admin/add")
    public String addPage(Model model) {
        model.addAttribute("allAuthorities", authorityService.getAllAuthorities());
        model.addAttribute("user", new User());
        return "addPage";
    }

    @PostMapping("/admin/add")
    public String addUser(RedirectAttributes redirectAttributes,
                          @ModelAttribute("user") User user,
                          @ModelAttribute("ROLE_ADMIN") String roleAdmin,
                          @ModelAttribute("ROLE_USER") String roleUser) {
        Collection<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityByName(roleAdmin));
        authorities.add(authorityService.getAuthorityByName(roleUser));
        user.setAuthorities(authorities);
        redirectAttributes.addAttribute("message", userService.addUser(user));
        return "redirect:/result";
    }

    @GetMapping("/admin/delete")
    public String deletePage(RedirectAttributes redirectAttributes,
                             Model model,
                             @RequestParam Long id) {
        if (id == 0) {
            model.addAttribute("username", "all users");
        } else {
            User user = userService.getUserById(id);
            if (user == null) {
                redirectAttributes.addAttribute("message", "Error: User does not exist!");
                return "redirect:/result";
            } else {
                model.addAttribute("id", id);
                model.addAttribute("username", user.getUsername());
            }
        }
        return "deletePage";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(RedirectAttributes redirectAttributes,
                             @RequestParam Long id) {
        redirectAttributes.addAttribute("message", userService.deleteUserById(id));
        return "redirect:/result";
    }

    @GetMapping("/admin/edit")
    public String editPage(Model model,
                           @RequestParam Long id) {
        User user = userService.getUserById(id);
        user.setPassword("");
        Collection<Authority> allRoles = authorityService.getAllAuthorities();
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("user", user);
        return "editPage";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @ModelAttribute("ROLE_ADMIN") String roleADmin,
                           @ModelAttribute("ROLE_USER") String roleUser,
                           RedirectAttributes redirectAttributes) {
        List<Authority> roles = new ArrayList<>();
        roles.add(authorityService.getAuthorityByName(roleADmin));
        roles.add(authorityService.getAuthorityByName(roleUser));
        user.setAuthorities(roles);
        redirectAttributes.addAttribute("message", userService.changeUser(user));
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultPage(Model model,
                             @ModelAttribute("message") String message) {
        model.addAttribute("message", message);
        return "resultPage";
    }
}
