package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityServiceImp;
import crud.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private UserServiceImp userService;
    private AuthorityServiceImp authorityService;

    @Autowired
    public AdminController(UserServiceImp userService,
                           AuthorityServiceImp authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String result = userService.addUser(user);
        if (result.contains("Error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/user-by-username")
    public ResponseEntity<User> getUserByUsername(@RequestBody String username) {
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/edit-user")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        String result = userService.editUser(user);
        if (result.contains("Error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        String result = userService.deleteUserById(user.getId());
        if (result.contains("Error")) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/all-users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/all-authorities")
    public ResponseEntity<Collection<Authority>> getAllAuthorities() {
        Collection<Authority> allAuthorities = authorityService.getAllAuthorities();
        return new ResponseEntity<>(allAuthorities, HttpStatus.OK);
    }

    @PostMapping("/authority-by-name")
    public ResponseEntity<Authority> getAuthorityById(@RequestBody String name) {
        Authority authority = authorityService.getAuthorityByName(name);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

}
