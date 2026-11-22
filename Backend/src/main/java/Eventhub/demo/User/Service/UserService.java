package Eventhub.demo.User.Service;


import Eventhub.demo.User.DTO.LoginReqUserDTO;
import Eventhub.demo.User.DTO.LoginResDTO;
import Eventhub.demo.User.DTO.SignupUserReqDTO;
import Eventhub.demo.User.DTO.UserResDTO;

public interface UserService {
    UserResDTO createUser(SignupUserReqDTO userReqDTO);
    LoginResDTO loginUser(LoginReqUserDTO loginReqUserDTO);
}
