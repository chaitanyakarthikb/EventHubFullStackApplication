package Eventhub.demo.User.Service;


import Eventhub.demo.User.DTO.UserReqDTO;
import Eventhub.demo.User.Entity.User;

public interface UserService {
    User createUser(UserReqDTO userReqDTO);
}
