package backend.controller;

import backend.model.User;
import backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll(){
        Iterable<User> users = userService.findAll();
        if (users.iterator().hasNext()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if (userService.existsByEmailOrUsername(user.getEmail(), user.getUsername())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable("id") Long id, @RequestBody User user){
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<?> detailUser(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if (user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        if (userService.checkLogin(user)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
