package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final HashMap<Integer, User> userHashMap = new HashMap<>();

    private int generateUniqueId = 1;

    @PostMapping()
    public User create(@Valid @RequestBody User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getName().isBlank() || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        user.setId(generateUniqueId);
        userHashMap.put(user.getId(), user);
        incrementUniqueId();
        log.info("Create USER {} success {}!", user.getId(), user);
        return user;
    }

    @GetMapping()
    public List<User> getAllUser() {
        return new ArrayList<>(userHashMap.values());
    }

    @PutMapping()
    public User updateUser(@Valid @RequestBody User user) {
        if (userHashMap.containsKey(user.getId())) {
            userHashMap.put(user.getId(), user);
            log.info("Update USER {} success {}", user.getId(), user);
            return user;
        } else {
            throw new NotFoundException(String.valueOf(user.getId()));
        }
    }

    private void incrementUniqueId() {
        generateUniqueId++;
    }

}
