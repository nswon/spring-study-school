package com.example.BoardGame.controller;

import com.example.BoardGame.entity.User;
import com.example.BoardGame.service.MyBatisService;
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
    private final MyBatisService myBatisService;

    @GetMapping("/select")
    public Map<String, Object> selectAllUser(){
        Map<String, Object> response = new HashMap<>();
//        List<User> userList = userService.selectAll();
//        System.out.println(userList)
//        response.put("result", userList);

        response.put("result", myBatisService.findAll());


        return response;
    }

}
