package com.example.BoardGame.service;

import com.example.BoardGame.entity.User;
import com.example.BoardGame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    //private final userRepository userRepository;
    private final UserRepository userRepository;

    public boolean login(User user) {
        User findUser = userRepository.findUser(user.getEmail(),user.getPassword());

        if(findUser == null){
            return false;
        }
        if(!findUser.getPassword().equals(user.getPassword())){
            return false;
        }
        return true;
    }
}
