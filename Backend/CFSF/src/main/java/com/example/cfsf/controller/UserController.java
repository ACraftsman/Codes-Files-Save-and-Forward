package com.example.cfsf.controller;

import com.example.cfsf.pojo.Result;
import com.example.cfsf.pojo.User;
import com.example.cfsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.example.cfsf.util.JwtUtil.createToken;

@RequestMapping("/user")
@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,12}$") String username, @Pattern(regexp = "^\\S{6,32}$") String password) {
        if (userService.findByUserName(username) != null) {
            return Result.error("已存在该用户");
        } else {
            int _ifSuccess = userService.register(username, password);
            if (_ifSuccess == -1) {
                return Result.error("注册失败");
            }else{
                User user = userService.findByUserName(username);
                HashMap<String, Object> _claims = new HashMap<>();
                _claims.put("username", user.getUsername());
                _claims.put("id", user.getId());
                String _token = createToken(_claims);
                return Result.success("注册成功",_token);
            }

        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,12}$") String username, @Pattern(regexp = "^\\S{6,32}$") String password) {
        User user = userService.findByUserName(username);
        if (user.getPassword().equals(password)) {
            HashMap<String, Object> _claims = new HashMap<>();
            _claims.put("username", user.getUsername());
            _claims.put("id", user.getId());
            String _token = createToken(_claims);
            return Result.success("登录成功",_token);
        }
        return Result.error("用户名不存在或密码错误");
    }
}
