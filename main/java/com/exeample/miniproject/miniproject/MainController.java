package com.example.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/miniproject")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/main")
    public String showMain() {
        logger.info("메인 페이지 요청");
        return "miniproject/main";
    }

    @GetMapping("/bookingairline")
    public String showBookingAirline() {
        logger.info("항공예약 페이지 요청");
        return "miniproject/bookingairline";
    }

    @GetMapping("/bookinghotal")
    public String showBookingHotal() {
        logger.info("호텔예약 페이지 요청");
        return "miniproject/bookinghotal";
    }
    
    @GetMapping("/login")
    public String redirectToLogin() {
        logger.info("로그인 페이지로 리디렉션");
        return "redirect:/miniproject/login";
    }
    
    @GetMapping("/signup")
    public String redirectToSignup() {
        logger.info("회원가입 페이지로 리디렉션");
        return "redirect:/miniproject/signup";
    }
}
