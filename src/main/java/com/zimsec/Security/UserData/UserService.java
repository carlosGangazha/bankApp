package com.zimsec.Security.UserData;

import com.zimsec.Security.userAuth.User;
import com.zimsec.Security.userAuth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return  users.stream().map( user -> new UserResponseDto(user.getFirstName(), user.getUsername())).toList();
    }
}
