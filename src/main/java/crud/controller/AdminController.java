package crud.controller;

import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/edit-user")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@Param("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}
