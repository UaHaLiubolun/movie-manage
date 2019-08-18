package movie.manage.demo;

import movie.manage.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Mono login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> token = new HashMap<>();
        token.put("token", "token");
        result.put("code", 20000);
        result.put("data", token);
        return Mono.just(result);
    }

    @GetMapping("/info")
    public Mono<Result> info() {
        Mono<User> userMono = userService.findByUsername("admin");
        return userMono.map(Result::success);
    }


    @PostMapping("")
    public Mono<User> save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return userService.deleteByUsername(username);
    }

    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("")
    public Flux<User> findAll() {
        return userService.findAll();
    }

}
