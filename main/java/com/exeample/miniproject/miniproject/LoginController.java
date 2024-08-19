package com.example.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miniproject/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    public String showLogin() {
        logger.info("로그인 페이지 요청");
        return "miniproject/login";
    }

    @PostMapping
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("password") String password,
                        Model m) {
        Login login = userService.authenticate(userId, password);

        if (login != null) {
            logger.info("로그인 성공: userId={}", userId);
            return "redirect:/miniproject/main";
        } else {
            logger.error("로그인 실패: 존재하지 않는 아이디나 비밀번호");
            m.addAttribute("error", "존재하지 않는 아이디나 비밀번호입니다");
            return "miniproject/login";
        }
    }
}
