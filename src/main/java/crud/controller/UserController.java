package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private AuthorityService authorityService;

    @Autowired
    public UserController(UserService userService, AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping("/info")
    public String infoPage(@Param("id") Long id,
                           Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authority adminAuthority = authorityService.getAuthorityByName("ROLE_ADMIN");
        Authority userAuthority = authorityService.getAuthorityByName("ROLE_USER");
        if (authUser.getAuthorities().contains(adminAuthority)) {
            model.addAttribute("user", userService.getUserById(id));
            model.addAttribute("isAdmin", true);
        } else if (authUser.getAuthorities().contains(userAuthority)) {
            model.addAttribute("user", userService.getUserById(authUser.getId()));
        }
        return "infoPage";
    }

}
