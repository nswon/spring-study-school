package com.example.BoardGame.controller;

import com.example.BoardGame.entity.User;
import com.example.BoardGame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/select")
    public Map<String, Object> selectAllUser() {
        System.out.println("aa1");
        Map<String, Object> response = new HashMap<>();
        System.out.println("aa2");
        List<User> userList = userService.selectAll();
        System.out.println("aa3");
        System.out.println(userList);
        response.put("result", userList);

        return response;
    }

}
