package Eventhub.demo.User.Service;


import Eventhub.demo.User.DTO.UserReqDTO;
import Eventhub.demo.User.DTO.UserResDTO;
import Eventhub.demo.User.Entity.User;

public interface UserService {
    UserResDTO createUser(UserReqDTO userReqDTO);
}
