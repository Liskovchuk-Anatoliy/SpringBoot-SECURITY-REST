package ru.itmentor.spring.boot_security.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.UserConfig.RestService;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.util.UserErrorResponse;
import ru.itmentor.spring.boot_security.demo.util.UserNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestControl {
    private final RestService restService;
    @Autowired
    public RestControl(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("")
    public List<User> getUser(){
        return restService.findAll();
    }

    @GetMapping("/find/{id}")
    public User getUser(@PathVariable("id") long id){
        return restService.findOne(id);

    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e){
        UserErrorResponse userErrorResponse = new UserErrorResponse("Нет такого юзера по такому id",System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }
}
