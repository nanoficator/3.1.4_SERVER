package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addUser(Model model,
                          @ModelAttribute("user") User user,
                          @ModelAttribute("ROLE_ADMIN") String roleAdmin,
                          @ModelAttribute("ROLE_USER") String roleUser) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleByName(roleAdmin));
        roles.add(roleService.getRoleByName(roleUser));
        user.setRoles(roles);
        model.addAttribute("message", userService.addUser(user));
        return "redirect:/result";
    }

    @GetMapping("/admin/delete")
    public String deletePage(Model model,
                             @RequestParam Long id) {
        if (id == 0) {
            model.addAttribute("username", "all users");
        } else {
            User userForDelete = userService.getUserById(id);
            if (userForDelete == null) {
                model.addAttribute("message", "Error: User does not exist!");
                return "redirect:/result";
            } else {
                model.addAttribute("id", id);
                model.addAttribute("userName", userForDelete.getUsername());
            }
        }
        return "deletePage";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(Model model,
                             @RequestParam Long id) {
        model.addAttribute("message", userService.deleteUserById(id));
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String resultPage(Model model,
                             @ModelAttribute("message") String message) {
        model.addAttribute("message", message);
        return "resultPage";
    }
}
