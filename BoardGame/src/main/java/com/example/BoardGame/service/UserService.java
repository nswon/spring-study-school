package com.example.BoardGame.service;

import com.example.BoardGame.entity.User;
import com.example.BoardGame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> selectAll() {
        return userRepository.findAll();
    }

}
