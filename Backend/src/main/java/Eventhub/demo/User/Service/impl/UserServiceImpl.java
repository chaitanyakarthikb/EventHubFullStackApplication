package Eventhub.demo.User.Service.impl;

import Eventhub.demo.User.DTO.LoginReqUserDTO;
import Eventhub.demo.User.DTO.SignupUserReqDTO;
import Eventhub.demo.User.DTO.UserResDTO;
import Eventhub.demo.User.Entity.User;
import Eventhub.demo.User.Repository.UserRepository;
import Eventhub.demo.User.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserResDTO createUser(SignupUserReqDTO userReqDTO) {
        log.info("Creating user with email:` {}", userReqDTO.getEmail());
        if(userRepository.findUserByEmail(userReqDTO.getEmail().trim().toLowerCase())!=null){
            log.error("User with email: {} already exists",userReqDTO.getEmail());
        throw new DataIntegrityViolationException(
            "User with email: " + userReqDTO.getEmail() + " already exists");
        }
        String hashedPassword = passwordEncoder.encode(userReqDTO.getPassword());
        User user = User.builder()
                .createdAt(Instant.now())
                .email(userReqDTO.getEmail().trim().toLowerCase())
                .fullName(userReqDTO.getFullName())
                .role(User.UserRoles.USER)
                .build();
        user.setPasswordHash(hashedPassword);
        User myUser =  userRepository.save(user);
        log.info("Created user and successfully saved in the DB : {}",myUser.getEmail());
        return UserResDTO.builder()
                .userId(myUser.getUserId())
                .createdAt(myUser.getCreatedAt())
                .email(myUser.getEmail())
                .role(myUser.getRole())
                .fullName(myUser.getFullName())
                .build();

    }

    @Override
    public UserResDTO loginUser(LoginReqUserDTO loginReqUserDTO){
        User user  = userRepository.findUserByEmail(loginReqUserDTO.getEmail());
        if (passwordEncoder.matches(loginReqUserDTO.getPassword(), user.getPasswordHash())){
            return UserResDTO.builder()
                    .userId(user.getUserId())
                    .role(user.getRole())
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .createdAt(user.getCreatedAt())
                    .build();
        }
        return null;
    }
}
