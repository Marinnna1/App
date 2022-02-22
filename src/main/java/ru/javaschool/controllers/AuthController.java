package ru.javaschool.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.dto.UserDto;
import ru.javaschool.enums.Position;


import ru.javaschool.security.JwtProvider;
import ru.javaschool.services.AuthService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("auth/")
public class AuthController {

    private static Logger log = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("signin")
    public String doSignIn(@RequestBody UserDto userDto) {
        String token = jwtProvider.generateToken(userDto.getName());
        log.info("post request for sign in received");
        return authService.findUserPosition(userDto.getName(), userDto.getPassword());
    }


    @PostMapping("signup")
    public String doSignUp(@RequestBody UserDto userDto) {
        log.info("post request for sign in received");
        return authService.saveUser(userDto.getName(), userDto.getPassword(), Position.valueOf(userDto.getPosition()));
    }



}