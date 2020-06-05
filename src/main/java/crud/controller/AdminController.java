package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
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

    @PostMapping(value = "/add-user",  produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        String result = userService.addUser(user);
        if (result.contains("Error")) {
            return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @PostMapping("/edit-user")
    public String editUser(@ModelAttribute User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
//        userService.deleteUserById(id);
        return new ResponseEntity<String >("test", HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<Collection<User>>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/all-authorities")
    public ResponseEntity<Collection<Authority>> getAllAuthorities() {
        Collection<Authority> allAuthorities = authorityService.getAllAuthorities();
        return new ResponseEntity<Collection<Authority>>(allAuthorities, HttpStatus.OK);
    }

    @GetMapping("/authority-by-name")
    public ResponseEntity<Authority> getAuthorityById(@RequestBody String name) {
        Authority authority = authorityService.getAuthorityByName(name);
        return new ResponseEntity<Authority>(authority, HttpStatus.OK);
    }
}
