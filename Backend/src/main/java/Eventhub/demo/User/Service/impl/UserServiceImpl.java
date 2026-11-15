package Eventhub.demo.User.Service.impl;

import Eventhub.demo.User.DTO.UserReqDTO;
import Eventhub.demo.User.Entity.User;
import Eventhub.demo.User.Repository.UserRepository;
import Eventhub.demo.User.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User createUser(UserReqDTO userReqDTO) {
        log.info("Creating user with email:` {}", userReqDTO.getEmail());
        User user = new User();
        user.setCreatedAt(Instant.now());
        user.setRole(userReqDTO.getRole());
        user.setFullName(userReqDTO.getFullName());
        user.setEmail(userReqDTO.getEmail());
        user.setPasswordHash(userReqDTO.getPasswordHash());
        return userRepository.save(user);
    }
}
