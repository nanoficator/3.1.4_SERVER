package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private UserService userService;
    private AuthorityService authorityService;

    public AdminController(UserService userService,
                          AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("newUser") User newUser,
                          @ModelAttribute("test") String test) {
        userService.addUser(newUser);
        return "redirect:/main";
    }

    @PostMapping("/edit-user")
    public String editUser(@ModelAttribute("editUser") User editUser) {
        return "redirect:/main";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@Param("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/main";
    }
}
