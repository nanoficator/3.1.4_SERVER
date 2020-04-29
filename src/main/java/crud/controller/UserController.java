package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String startPage() {
        return "redirect:/login";
    }

    @GetMapping("/admin/table")
    public String tablePage(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "tablePage";
    }

    @GetMapping("/admin/add")
    public String addPage(Model model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("user", new User());
        return "addPage";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user,
                          @ModelAttribute("ROLE_ADMIN") String roleAdmin,
                          @ModelAttribute("ROLE_USER") String roleUser,
                          Model model) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(roleAdmin));
        roles.add(roleService.getRoleByName(roleUser));
        user.setRoles(roles);
        model.addAttribute("message", userService.addNewUser(user));
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultPage(@ModelAttribute("message") String message) {
        return "resultPage";
    }
}
