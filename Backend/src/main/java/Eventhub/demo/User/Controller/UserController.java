package Eventhub.demo.User.Controller;

import Eventhub.demo.User.DTO.UserReqDTO;
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
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody @Valid UserReqDTO userReqDTO){
        log.info("Request received to create user with email: {}",userReqDTO.getEmail());
        UserResDTO myUser = userService.createUser(userReqDTO);
        HashMap<String, Object>response = new HashMap<>();
        response.put("apiStatus","success");
        response.put("user",myUser);
        return ResponseEntity.status(201).body(response);
    }



}
