package cat.tecnocampus.rest;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.domainController.UserUseCases;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
public class UserRESTController {
    private UserUseCases userUseCases;

    public UserRESTController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserLab> listUsers() {
        return userUseCases.getUsers();
    }

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLab showUser(@PathVariable String username) {
        return userUseCases.getUser(username);
    }

    @GetMapping(value = "/users/exists/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existsUser(@PathVariable String username) {
        return userUseCases.userExists(username);
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLab createUser(@RequestBody @Valid UserLab user) {

        userUseCases.registerUser(user);

        return user;
    }
}
