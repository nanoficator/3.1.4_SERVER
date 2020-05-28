package crud.controller;

import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private UserService userService;
    private AuthorityService authorityService;

    public TestController(UserService userService,
                           AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public ResponseEntity<User> testPage() {
        Long l = Long.parseLong("1");
        User user = userService.getUserById(l);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
