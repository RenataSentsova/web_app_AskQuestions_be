package service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.response.ResponseMessage;
import service.services.UsersService;
import service.transfer.UserDto;

import javax.validation.ValidationException;
import java.util.List;

import static service.transfer.UserDto.from;

@CrossOrigin
@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return from(usersService.findAll());
    }

    @GetMapping("/users/{user-id}")
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return from(usersService.findOne(userId));
    }

    @GetMapping("/user/{username}")
    public Long getUserIdByUserName(@PathVariable("username") String username){
        return usersService.getUserIdByUsername(username);
    }

    @PostMapping("/editor/deleteuser")
    public ResponseEntity<?> deleteUser(@RequestBody String login, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        usersService.makeNonActive(login);
        return new ResponseEntity<>(new ResponseMessage("Status change was successful"), HttpStatus.OK);
    }

}
