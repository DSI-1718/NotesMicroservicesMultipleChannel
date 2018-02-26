package cat.tecnocampus.rest;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.domainController.UserUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRESTController {
    private UserUseCases userUseCases;

    public UserRESTController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserLab> listUsers() {
        return userUseCases.getUsers();
    }

    @GetMapping(value = "/api/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLab showUser(@PathVariable String username) {
        return userUseCases.getUser(username);
    }

    @GetMapping(value = "/api/users/exists/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existsUser(@PathVariable String username) {
        return userUseCases.userExists(username);
    }

    @PostMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLab createUser(@RequestBody @Valid UserLab user) {

        userUseCases.registerUser(user);

        return user;
    }

    @DeleteMapping(value = "/api/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@PathVariable String username) {
        UserLab user;

        if (userUseCases.userExists(username)) {
            user = userUseCases.getUser(username);
            userUseCases.deleteUser(username);
            return new ResponseEntity(user, HttpStatus.OK);
        }

        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/")
    public String ribbonConnect() {
        return "Ribbon connect";
    }
}
