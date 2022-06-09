package com.example.BoardGame.controller;

import com.example.BoardGame.entity.User;
import com.example.BoardGame.service.LoginService;
import com.example.BoardGame.service.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }


    @GetMapping("/login")
    @ResponseBody
    public String loginId(@ModelAttribute User user) {
        System.out.println("로그인22");
        if(loginService.login(user)){

            return "Ok";
        }else{
            return "Fail";
        }

    }

    @GetMapping("/login")
    @ResponseBody
    public String loginId(@ModelAttribute User user, HttpServletRequest request) {
        if(loginService.login(user)){
            HttpSession session = request.getSession();
            session.setAttribute(SessionConst.LOGIN_USER,user);
            System.out.println("세션추가");

            return "Ok";
        }else{
            return "Fail";
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    public Map loginId(HttpServletRequest request) {
        Map result = new HashMap();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            result.put("code",200);
            result.put("msg","정상적으로 로그아웃 되었습니다.");
        }
        else{
            result.put("code",400);
            result.put("msg","세션이 없습니다.");
        }

        return result;
    }
}
