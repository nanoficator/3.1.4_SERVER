package crud.controller;

import com.mysql.cj.xdevapi.JsonArray;
import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String result = userService.addUser(user);
        if (result.contains("Error")) {
            return new ResponseEntity<String >(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/edit-user")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        String result = userService.editUser(user);
        if (result.contains("Error")) {
            return new ResponseEntity<String >(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        String result = userService.deleteUserById(user.getId());
        if (result.contains("Error")) {
            return new ResponseEntity<String >(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>(result, HttpStatus.OK);
        }    }

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
