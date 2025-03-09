package com.demo.demo.signin;

import com.demo.demo.database.maria.User;
import com.demo.demo.database.maria.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/signin")
@RestController
@ControllerAdvice
public class CheckIdPw {
    @Autowired
    UserService userService;

    @PostMapping("/check")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody UserIdPw userIdPw) {
        Map<String, String> response = new HashMap<>();
        User user;
        if (Objects.equals(userIdPw.getUsername(), "") && Objects.equals(userIdPw.getPassword(), "")) {
            response.put("message", "아이디와 비밀번호를 입력해주세요");
        } else if (Objects.equals(userIdPw.getPassword(), "")) {
            response.put("message", "비밀번호를 입력해주세요");
        } else if (Objects.equals(userIdPw.getUsername(), "")) {
            response.put("message", "아이디를 입력해주세요");
        } else {
            user = userService.findByMId(userIdPw.getUsername());
            if (user != null) {
                if (userService.checkPassword(user, userIdPw.getPassword())) {
                    response.put("message", "환영합니다 " + user.getMId() + "님");
                } else {
                    response.put("message", "비밀번호가 틀렸습니다");
                }
            } else {
                response.put("message", "아이디가 틀렸습니다");
            }
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @ResponseBody
    public boolean logout(@RequestBody UserIdPw userIdPw) {
        return userService.checkPassword(userService.findByMId(userIdPw.getUsername()), userIdPw.getPassword());
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<Map<String, String>> signin(@RequestBody UserIdPw userIdPw) {
        Map<String, String> response = new HashMap<>();
        if (userService.findByMId(userIdPw.getUsername()) == null) {
            User user = new User();
            String userId = userIdPw.getUsername();
            String userPw = userIdPw.getPassword();
            String userName = userIdPw.getName();
            user.setMId(userId);
            user.setMPw(userPw);
            user.setMName(userName);
            userService.saveUser(user);
            response.put("id", userId);
            response.put("pw", userPw);
            response.put("name", userName);
            response.put("message", "회원가입이 완료되었습니다");
        } else {
            response.put("message", "이미 존재하는 아이디 입니다");
        }
        return ResponseEntity.ok(response);
    }
}
