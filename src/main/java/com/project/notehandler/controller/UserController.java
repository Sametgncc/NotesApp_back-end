package com.project.notehandler.controller;

import com.project.notehandler.model.Note;
import com.project.notehandler.model.User;
import com.project.notehandler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    /* @Autowired*/
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }
    @DeleteMapping("/{email}")
    public User deleteByEmail(@PathVariable String email) {
        return userService.deleteByEmail(email);
    }
    @PutMapping("/{email}")
    public User updateByEmail(@RequestBody User user,@PathVariable String email) {
        return userService.updateByEmail(email,user);
    }
    @GetMapping("/{emailUsername}/{password}")
    public User checkUser(@PathVariable String emailUsername,@PathVariable String password) {
        return userService.checkUser(emailUsername,password);
    }

    @PutMapping("/{email}/addNote")
    public User addNoteByEmail(@RequestBody Note note, @PathVariable String email) {
        return userService.addNote(email,note);
    }
}
