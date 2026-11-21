package Eventhub.demo.User.Controller;

import Eventhub.demo.User.DTO.LoginReqUserDTO;
import Eventhub.demo.User.DTO.SignupUserReqDTO;
import Eventhub.demo.User.DTO.UserResDTO;
import Eventhub.demo.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody @Valid SignupUserReqDTO userReqDTO){
        log.info("Request received to create user with email: {}",userReqDTO.getEmail());
        UserResDTO myUser = userService.createUser(userReqDTO);
        HashMap<String, Object>response = new HashMap<>();
        response.put("apiStatus","success");
        response.put("user",myUser);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody LoginReqUserDTO loginUserReqDTO){
        log.info("Request received to login user with email: {}",loginUserReqDTO.getEmail());
        HashMap<String,Object>response = new HashMap<>();
        UserResDTO userResDTO = userService.loginUser(loginUserReqDTO);
        if (userResDTO!=null){
            response.put("loginStatus","success");
            response.put("user",userResDTO);
            return ResponseEntity.status(200).body(response);
        }
        response.put("loginStatus","Failed");
        response.put("message","Invalid Credentials");
        return ResponseEntity.status(401).body(response);
    }



}
