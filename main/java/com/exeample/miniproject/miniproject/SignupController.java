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
@RequestMapping("/miniproject/signup")
public class SignupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    public String showSignup() {
        logger.info("회원가입 페이지 요청");
        return "miniproject/signup";
    }

    @PostMapping
    public String signup(@RequestParam("userName") String userName,
                         @RequestParam("userIdCard") String userIdCard,
                         @RequestParam("phone") String phone,
                         @RequestParam("email") String email,
                         @RequestParam("address") String address,
                         @RequestParam("userId") String userId,
                         @RequestParam("password") String password,
                         Model m) {
        logger.info("회원가입 요청: userName={}, userIdCard={}, phone={}, email={}, address={}, userId={}",
                userName, userIdCard, phone, email, address, userId);

        try {
            Login login = new Login();
            login.setUserName(userName);
            login.setUserIdCard(userIdCard);
            login.setPhone(phone);
            login.setEmail(email);
            login.setAddress(address);
            login.setuserId(userId);
            login.setPassword(password);

            userService.insertUser(login);
            logger.info("회원가입 성공: userName={}", userName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("회원가입 과정에서 문제 발생!!", e);
            m.addAttribute("error", "회원가입이 정상적으로 완료되지 않았습니다!!");
            return "miniproject/signup";
        }

        return "redirect:/miniproject/login";
    }
}
